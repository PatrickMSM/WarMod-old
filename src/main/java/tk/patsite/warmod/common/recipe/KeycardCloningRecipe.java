/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package tk.patsite.warmod.common.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.registry.WarmodItems;

public class KeycardCloningRecipe extends SpecialCraftingRecipe {
    public KeycardCloningRecipe(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean hasEmpty = false, hasWritten = false;

        for(int i = 0; i < craftingInventory.size(); i++) {
            ItemStack currentStack = craftingInventory.getStack(i);
            if(currentStack.isOf(WarmodItems.WRITTEN_KEYCARD_ITEM)) {
                if(!hasWritten) {
                    hasWritten = true;
                } else {
                    return false;
                }
            }
            if(currentStack.isOf(WarmodItems.EMPTY_KEYCARD_ITEM)) {
                if(!hasEmpty) {
                    hasEmpty = true;
                } else {
                    return false;
                }
            }
        }

        return hasEmpty && hasWritten;
    }

    @Override
    public ItemStack craft(CraftingInventory craftingInventory) {

        boolean hasEmpty = false, hasWritten = false;
        ItemStack writtenKeycard = ItemStack.EMPTY;

        for(int i = 0; i < craftingInventory.size(); i++) {
            ItemStack currentStack = craftingInventory.getStack(i);
            if(currentStack.isOf(WarmodItems.WRITTEN_KEYCARD_ITEM)) {
                if(!hasWritten) {
                    writtenKeycard = currentStack;
                    hasWritten = true;
                } else {
                    return ItemStack.EMPTY;
                }
            }
            if(currentStack.isOf(WarmodItems.EMPTY_KEYCARD_ITEM)) {
                if(!hasEmpty) {
                    hasEmpty = true;
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if(!hasEmpty || !hasWritten)
            return ItemStack.EMPTY;

        if(writtenKeycard.getNbt().getBoolean("cloned"))
            return ItemStack.EMPTY;

        ItemStack clonedKeycard = new ItemStack(WarmodItems.WRITTEN_KEYCARD_ITEM, 1);
        clonedKeycard.setNbt(writtenKeycard.getNbt().copy());
        clonedKeycard.getNbt().putBoolean("cloned", true);
        return clonedKeycard;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory craftingInventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(craftingInventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            ItemStack currentStack = craftingInventory.getStack(i);
            if(currentStack.isOf(WarmodItems.WRITTEN_KEYCARD_ITEM)) {
                defaultedList.set(i, currentStack);
            }
        }
        return defaultedList;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Warmod.KEYCARD_CLONING_RECIPE_SERIALIZER;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 3 && height >= 3;
    }
}

