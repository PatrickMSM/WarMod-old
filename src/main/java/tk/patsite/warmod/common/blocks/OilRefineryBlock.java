package tk.patsite.warmod.common.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tk.patsite.warmod.common.blocks.entites.OilRefineryBlockEntity;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;

public class OilRefineryBlock extends BlockWithEntity {
    public OilRefineryBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OilRefineryBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity cast to
            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                //With this call the server will request the client to open the appropriate Screen handler
                player.openHandledScreen(screenHandlerFactory);
                Entity
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, WarmodBlockEntities.OIL_REFINERY_BLOCK_ENTITY, OilRefineryBlockEntity::tick);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.cuboid(0.3125, 0, 0.125, 0.6875, 0.875, 0.5);

        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.5, 0.9375, 0.875, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.5, 0.4375, 0.875, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.5, 0.5625, 0.125, 0.875));

        return shape;
    }
}
