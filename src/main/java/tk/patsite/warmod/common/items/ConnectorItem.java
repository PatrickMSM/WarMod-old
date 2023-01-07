package tk.patsite.warmod.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tk.patsite.warmod.common.Util.ConnectableBlock;
import tk.patsite.warmod.common.Util.ConnectableBlockEntity;

import java.util.List;

/**
 * @see ConnectableBlock
 */
public class ConnectorItem extends Item {
    private static final String PARENT_BLOCK_POS_KEY = "ParentBlockPos";
    private static final String CHILD_BLOCK_POS_KEY = "ChildBlockPos";


    public ConnectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState blockToConnect = context.getWorld().getBlockState(context.getBlockPos());
        if(blockToConnect.getBlock() instanceof ConnectableBlock) {
            ItemStack stack = context.getStack();
            NbtCompound stackNbt = stack.getOrCreateNbt();

            if(context.getWorld().getBlockEntity(context.getBlockPos()) instanceof ConnectableBlockEntity blockEntityToConnect) {
                if(blockEntityToConnect.isConnected())
                    return ActionResult.PASS;

                if(blockEntityToConnect.isParent()) {
                    stackNbt.putLong(PARENT_BLOCK_POS_KEY, context.getBlockPos().asLong());
                    tryConnecting(stackNbt, context.getWorld(), context.getPlayer());
                } else {
                    stackNbt.putLong(CHILD_BLOCK_POS_KEY, context.getBlockPos().asLong());
                    tryConnecting(stackNbt, context.getWorld(), context.getPlayer());
                }
            } else {
                stackNbt.putLong(CHILD_BLOCK_POS_KEY, context.getBlockPos().asLong());
                tryConnecting(stackNbt, context.getWorld(), context.getPlayer());
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private void tryConnecting(NbtCompound connectorNbt, World world, PlayerEntity player) {
        if(connectorNbt.contains(PARENT_BLOCK_POS_KEY) && connectorNbt.contains(CHILD_BLOCK_POS_KEY)) {
            BlockPos parentBlockPos = BlockPos.fromLong(connectorNbt.getLong(PARENT_BLOCK_POS_KEY));
            BlockPos childBlockPos = BlockPos.fromLong(connectorNbt.getLong(CHILD_BLOCK_POS_KEY));

            if(world.getBlockEntity(parentBlockPos) instanceof ConnectableBlockEntity parentBlockEntity) {
                boolean connected = parentBlockEntity.tryConnectWith(childBlockPos, world);
                if(connected) {
                    connectorNbt.remove(PARENT_BLOCK_POS_KEY);
                    connectorNbt.remove(CHILD_BLOCK_POS_KEY);
                    player.sendMessage(Text.translatable("item.warmod.connectoritem.actionbar.connected", parentBlockPos, childBlockPos), true);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.warmod.connectoritem.tooltip.line1").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("item.warmod.connectoritem.tooltip.line2").formatted(Formatting.WHITE));
        tooltip.add(Text.translatable("item.warmod.connectoritem.tooltip.line3").formatted(Formatting.WHITE));
    }
}
