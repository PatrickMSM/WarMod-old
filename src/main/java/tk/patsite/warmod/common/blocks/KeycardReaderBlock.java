package tk.patsite.warmod.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.blocks.entites.KeycardReaderBlockEntity;
import tk.patsite.warmod.common.registry.WarmodItems;

import java.util.UUID;

public class KeycardReaderBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final BooleanProperty POWERED = Properties.POWERED;
    private static final int POWERED_DELAY = 40;

    public KeycardReaderBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(hand).isOf(WarmodItems.WRITTEN_KEYCARD_ITEM) && !state.get(POWERED)) {
            if(!world.isClient()) {
                ItemStack writtenKeycardStack = player.getStackInHand(hand);
                NbtCompound writtenKeycardNbt = writtenKeycardStack.getOrCreateNbt();
                KeycardReaderBlockEntity blockEntity = (KeycardReaderBlockEntity) world.getBlockEntity(pos);

                if (writtenKeycardNbt.getUuid(Warmod.WRITTEN_KEYCARD_UUID) != null && blockEntity != null) {
                    UUID uuid = writtenKeycardNbt.getUuid(Warmod.WRITTEN_KEYCARD_UUID);
                    if (blockEntity.readOrWriteUUID(uuid)) {
                        world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_ALL);
                        world.updateNeighbors(pos, this);
                        world.createAndScheduleBlockTick(pos, this, POWERED_DELAY);

                    }
                }
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!state.get(POWERED)) {
            return;
        }
        world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_ALL);
        world.updateNeighbors(pos, this);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(POWERED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeycardReaderBlockEntity(pos, state);
    }
}
