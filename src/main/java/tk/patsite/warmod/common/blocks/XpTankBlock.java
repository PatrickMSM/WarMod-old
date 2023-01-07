package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tk.patsite.warmod.common.blocks.entites.XpTankBlockEntity;

public class XpTankBlock extends Block implements BlockEntityProvider {
    public XpTankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new XpTankBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // TODO: does not work with checking is client, test why
        if(world.isClient()) {
            System.out.println("isClient");
            return ActionResult.SUCCESS;
        }
        System.out.println("isNotClient");

        XpTankBlockEntity be = (XpTankBlockEntity) world.getBlockEntity(pos);
        if(be.canAdd() && player.experienceLevel > 0) {
            be.addXp();
            player.addExperienceLevels(-1);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if(world.isClient()) {
            return;
        }

        if(!player.isSneaking()) {
            XpTankBlockEntity be = (XpTankBlockEntity) world.getBlockEntity(pos);
            if(be.canRemove()) {
                be.removeXp();
                player.addExperienceLevels(1);
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.cuboid(0.125, 0, 0.125, 0.875, 1, 0.875);

        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.8125, 0.625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 1, 0.3125, 0.6875, 1.0625, 0.6875));

        return shape;
    }
}
