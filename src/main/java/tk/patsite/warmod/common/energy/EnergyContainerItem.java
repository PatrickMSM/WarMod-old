package tk.patsite.warmod.common.energy;

import net.minecraft.item.ItemStack;

public interface EnergyContainerItem {
    int getMaxEnergy();
    int getEnergy(ItemStack stack);
    void discharge(int amount, ItemStack stack);
    default boolean isEmpty(ItemStack stack) {
        return getEnergy(stack) <= 1;
    };
    default void discharge(ItemStack stack) {
        discharge(1, stack);
    }
}
