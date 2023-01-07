package tk.patsite.warmod.common.entities;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Util.Util;
import tk.patsite.warmod.common.registry.WarmodEntities;

public class WaterDropEntity extends ProjectileEntity {
    public WaterDropEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    public WaterDropEntity(World world, double x, double y, double z) {
        this(WarmodEntities.WATER_DROP_ENTITY, world);
        this.setPosition(x, y, z);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(hitResult.getType() != HitResult.Type.MISS) {
            if (!this.world.isClient()) {
                this.world.sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
                Util.waterInBox(this.world, new Box(this.getBlockPos()).expand(2));
                this.kill();
            }
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = ParticleTypes.SPLASH;
            for (int i = 0; i < 50; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isSubmergedInWater())
            this.kill();

        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        this.onCollision(hitResult);

        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
    }

    @Override
    protected void initDataTracker() {}
}
