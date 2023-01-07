package tk.patsite.warmod.common.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.energy.EnergyContainerItemSlot;

public class OilRefineryScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public OilRefineryScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(1));
    }

    public OilRefineryScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Warmod.OIL_REFINERY_SCREEN_HANDLER, syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        this.addProperties(propertyDelegate);

        //This will place the slot in the correct locations for 3 slots. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        //Our inventory
        // TODO: fix the positioning (-2 y)
        this.addSlot(new EnergyContainerItemSlot(inventory, 0, 18, 32));
        this.addSlot(new Slot(inventory, 1, 56, 35));
        this.addSlot(new Slot(inventory, 2, 116, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        //The player inventory
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        //The player Hotbar
        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    public int getCookProgress() {
        return propertyDelegate.get(0) * 28 / Warmod.OIL_REFINERY_MAX_COOK_TIME;
    }

    public void setMode(int mode) { propertyDelegate.set(1, mode); }


    // boilerplate
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
