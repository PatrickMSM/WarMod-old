package tk.patsite.warmod.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.patsite.warmod.common.registry.WarmodEntities;
import tk.patsite.warmod.common.registry.WarmodItems;

public class FastMinecartEntity extends MinecartEntity {
    public FastMinecartEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public FastMinecartEntity(World world, double x, double y, double z) {
        this(WarmodEntities.FAST_MINECART_ENTITY, world);
        this.setPosition(x, y, z);
    }

    @Override
    public double getMaxSpeed() {
        return (this.isTouchingWater() ? 15.0 : 30) / 20.0;
    }

    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(WarmodItems.FAST_MINECART_ITEM);
    }
}
