package tk.patsite.warmod.common.blocks;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import tk.patsite.warmod.common.entities.WaterDropEntity;

public class WaterCannonBlock extends CannonBlock {
    public WaterCannonBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void launchProjectile(ServerWorld world, Direction direction, BlockPos pos) {
        WaterDropEntity waterDrop = new WaterDropEntity(world, pos.getX(), pos.getY(), pos.getZ());
        Vec3d velocity = Vec3d.of(direction.getVector());
        velocity = velocity.multiply(0.5); // HORIZONTAL
        velocity = velocity.add(0, 1, 0); // VERTICAL
        waterDrop.setVelocity(velocity);
        world.spawnEntity(waterDrop);
    }

    @Override
    protected int getDelay() {
        return 10;
    }

    @Override
    protected boolean canShoot(ServerWorld world, BlockPos pos) {
        return true;
    }
}
