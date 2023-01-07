package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import tk.patsite.warmod.common.entities.WaterDropEntity;

public class SprinklerBlock extends Block {
    private static final int DELAY = 10;

    public SprinklerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos)) {

            WaterDropEntity waterDrop = new WaterDropEntity(world, pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D);
            waterDrop.setVelocity(0, -1, 0);
            world.spawnEntity(waterDrop);

            world.createAndScheduleBlockTick(pos, this, DELAY);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            scheduleTick(world, pos);
        }
    }

    private void scheduleTick(World world, BlockPos pos) {
        if(!world.isClient() && !world.getBlockTickScheduler().isQueued(pos, this)) {
            world.createAndScheduleBlockTick(pos, this, DELAY);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.offset(Direction.UP), Direction.DOWN);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.cuboid(0.375, 0.9375, 0.375, 0.625, 1, 0.625);

        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.46875, 0.8125, 0.46875, 0.53125, 0.9375, 0.53125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.4375, 0.5625, 0.8125, 0.5625));

        return shape;
    }
}
