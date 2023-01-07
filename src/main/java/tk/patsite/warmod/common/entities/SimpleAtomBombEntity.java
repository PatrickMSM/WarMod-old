package tk.patsite.warmod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Util.Util;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.registry.WarmodEntities;

public class SimpleAtomBombEntity extends Entity {
    private static final double SPEED = -20.0F/20.0F; // change first number, second is for ticks
    private static final float DAMAGE = 1700.0f;

    public SimpleAtomBombEntity(EntityType<?> type, World world) {
        super(type, world);
        this.setNoGravity(true);
    }

    public SimpleAtomBombEntity(World world, double x, double z) {
        this(WarmodEntities.SIMPLE_ATOM_BOMB_ENTITY, world);
        this.setPosition(x, world.getDimension().height(), z);
    }

    @Override
    protected void initDataTracker() {}
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public Packet<?> createSpawnPacket() {
        LocateCommand
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
        setPosition(getPos().add(0, SPEED, 0));
        if(getWorld().getBlockState(getBlockPos()) != null && !getWorld().getBlockState(getBlockPos()).isAir()) {
            explode();
        }
        if(getPos().getY() <= getWorld().getBottomY()) {
            kill();
        }
    }

    private void explode() {
        kill();
        playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 500, 1);
        Util.sphericalStrongExplosion(getWorld(), getBlockPos(), 20, 40, DAMAGE, Warmod.ATOM_BOMB_DAMAGE_SOURCE, this);
    }
}
