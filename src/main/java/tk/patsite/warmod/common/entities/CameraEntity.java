package tk.patsite.warmod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;
import tk.patsite.warmod.common.registry.WarmodEntities;

public class CameraEntity extends Entity {
    public CameraEntity(EntityType<?> type, World world) {
        super(type, world);
    }
    public CameraEntity(World world, double x, double y, double z) {
        this(WarmodEntities.CAMERA_ENTITY, world);
        this.setPosition(x, y, z);
    }

    @Override
    protected void initDataTracker() {}
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
