package tk.patsite.warmod.common.blocks.entites;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tk.patsite.warmod.common.Util.ImplementedInventory;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.energy.EnergyContainerItem;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;
import tk.patsite.warmod.common.registry.WarmodItems;
import tk.patsite.warmod.common.screenhandlers.OilRefineryScreenHandler;

public class OilRefineryBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private int refineryProgress;
    private int mode = 0;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> refineryProgress;
                case 1 -> mode;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> refineryProgress = value;
                case 1 -> mode = value;
            };
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public OilRefineryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public OilRefineryBlockEntity(BlockPos pos, BlockState state) {
        this(WarmodBlockEntities.OIL_REFINERY_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be) {
        if (!world.isClient()) {
            OilRefineryBlockEntity blockEntity = (OilRefineryBlockEntity) be;

            ItemStack batteryStack = blockEntity.inventory.get(0);
            ItemStack oilStack = blockEntity.inventory.get(1);
            ItemStack resultStack = blockEntity.inventory.get(2);

            if( batteryStack.getItem() instanceof EnergyContainerItem battery   &&
                oilStack.isOf(WarmodItems.CRUDE_OIL_BUCKET)                     &&
                resultStack.getMaxCount() > resultStack.getCount()              &&
                !battery.isEmpty(batteryStack)                                  ) {

                if(blockEntity.refineryProgress >= Warmod.OIL_REFINERY_MAX_COOK_TIME) {
                    blockEntity.inventory.set(2, blockEntity.getModeItemStack());
                    blockEntity.inventory.set(1, ItemStack.EMPTY);
                    blockEntity.propertyDelegate.set(0, 0);
                    return;
                }

                blockEntity.refineryProgress++;
                battery.discharge(batteryStack);
            } else {
                if(blockEntity.refineryProgress > 0)
                    blockEntity.refineryProgress--;
            }
        }
    }

    private ItemStack getModeItemStack() {
        return switch (mode) {
            case 0 -> new ItemStack(WarmodItems.FUEL_BUCKET, 1);
            case 1 -> new ItemStack(WarmodItems.PLASTIC_ITEM, 1);
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new OilRefineryScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }
}
