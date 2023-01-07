package tk.patsite.warmod.common.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tk.patsite.warmod.common.registry.WarmodBlocks;

@Mixin(RedstoneWireBlock.class)
public abstract class RedstoneWireConnections {
    @Inject(at = @At("HEAD"), method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", cancellable = true)
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if(state.isOf(WarmodBlocks.KEYCARD_READER_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
