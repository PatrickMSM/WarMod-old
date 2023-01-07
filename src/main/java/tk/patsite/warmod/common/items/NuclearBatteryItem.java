package tk.patsite.warmod.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tk.patsite.warmod.common.energy.EnergyContainerItem;

public class NuclearBatteryItem extends Item implements EnergyContainerItem {
    public NuclearBatteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxEnergy() {
        return getMaxDamage();
    }

    @Override
    public int getEnergy(ItemStack stack) {
        return getMaxDamage() - stack.getDamage();
    }

    @Override
    public void discharge(int amount, ItemStack stack) {
        stack.setDamage(stack.getDamage() + amount);
    }
}
