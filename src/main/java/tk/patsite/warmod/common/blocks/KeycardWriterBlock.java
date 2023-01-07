package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.registry.WarmodItems;

import java.util.UUID;

public class KeycardWriterBlock extends Block {
    public KeycardWriterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(hand).isOf(WarmodItems.EMPTY_KEYCARD_ITEM)) {
            ItemStack writtenKeycardStack = new ItemStack(WarmodItems.WRITTEN_KEYCARD_ITEM);
            writtenKeycardStack.getOrCreateNbt().putUuid(Warmod.WRITTEN_KEYCARD_UUID, UUID.randomUUID());
            writtenKeycardStack.getOrCreateNbt().putBoolean("cloned", false);
            player.setStackInHand(hand, writtenKeycardStack);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }
}
