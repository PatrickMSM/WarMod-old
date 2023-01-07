package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import tk.patsite.warmod.common.Util.Util;

public class IronGeneratorBlock extends Block {
    private static final int GENERATOR_DELAY = 30;
    private static final ItemStack GENERATOR_STACK = new ItemStack(Items.IRON_INGOT, 5);


    public IronGeneratorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        scheduleTick(world, pos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBlockEntity(pos.down()) != null) {
            if(world.getBlockEntity(pos.down()) instanceof HopperBlockEntity) {
                Util.addItemToInventory((Inventory) world.getBlockEntity(pos.down()), GENERATOR_STACK);
            }
        }
        world.createAndScheduleBlockTick(pos, this, GENERATOR_DELAY * 20);
    }

    private void scheduleTick(WorldAccess world, BlockPos pos) {
        if(!world.isClient() && !world.getBlockTickScheduler().isQueued(pos, this)) {
            world.createAndScheduleBlockTick(pos, this, GENERATOR_DELAY * 20);
        }
    }
}
