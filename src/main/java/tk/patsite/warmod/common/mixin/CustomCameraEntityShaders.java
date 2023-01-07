package tk.patsite.warmod.common.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.entities.CameraEntity;

@Mixin(GameRenderer.class)
public abstract class CustomCameraEntityShaders {
    @Invoker
    abstract void callLoadShader(Identifier identifier);

    @Inject(at = @At("TAIL"), method = "onCameraEntitySet")
    private void onCameraEntitySet(Entity entity, CallbackInfo ci) {
        if (entity instanceof CameraEntity) {
            this.callLoadShader(Warmod.MONOCHROME_SHADER);
        }
    }
}
