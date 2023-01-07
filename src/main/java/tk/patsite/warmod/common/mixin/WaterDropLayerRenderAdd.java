package tk.patsite.warmod.common.mixin;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.patsite.warmod.client.WarmodClient;

@Mixin(WorldRenderer.class)
public abstract class WaterDropLayerRenderAdd {
    /* when this gets fixed then ill use this until then I shadow the thing
    https://discord.com/channels/507304429255393322/807617700734042122/1008464288584826982
    @ModifyVariable(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 4)
    )
    private VertexConsumerProvider.Immediate addToRender(VertexConsumerProvider.Immediate immediate, MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix) {
        immediate.draw(WarmodClient.CIRCLE_LAYER);
        return immediate;
    }*/

    @Final
    @Shadow
    private BufferBuilderStorage bufferBuilders;

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 4)
    )
    private void addToRender(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
        VertexConsumerProvider.Immediate immediate = bufferBuilders.getEffectVertexConsumers();
        immediate.draw(WarmodClient.CIRCLE_LAYER);
    }
}
