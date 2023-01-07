package tk.patsite.warmod.common.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.patsite.warmod.client.WarmodClient;

@Mixin(BufferBuilderStorage.class)
public abstract class RenderLayersToEntityBuilders {
    @Shadow
    private static void assignBufferBuilder(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> builderStorage, RenderLayer layer) {}

    @Inject(method = "method_22999", at = @At("TAIL"))
    private void addRenderLayers(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> map, CallbackInfo ci) {
        assignBufferBuilder(map, WarmodClient.CIRCLE_LAYER);
    }
}
