package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public abstract class CannonBlock extends HorizontalFacingBlock {
    static final int DELAY = 10;

    public CannonBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if(world.isReceivingRedstonePower(pos)) {
            scheduleTick(world, pos);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
       // builder.add(Properties.POWERED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            scheduleTick(world, pos);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos)) {
            if(canShoot(world, pos)) {
                launchProjectile(world, state.get(FACING), pos.add(state.get(FACING).getVector()));//pos.add(state.get(FACING).getVector()).add(0, 1, 0));
            }

            world.createAndScheduleBlockTick(pos, this, getDelay());
        }
    }

    protected abstract void launchProjectile(ServerWorld world, Direction direction, BlockPos pos);
    protected abstract int getDelay();
    protected abstract boolean canShoot(ServerWorld world, BlockPos pos);

    private void scheduleTick(World world, BlockPos pos) {
        if(!world.isClient() && !world.getBlockTickScheduler().isQueued(pos, this)) {
            world.createAndScheduleBlockTick(pos, this, getDelay());
        }
    }
}
