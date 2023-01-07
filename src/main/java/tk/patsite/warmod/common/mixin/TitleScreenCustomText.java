package tk.patsite.warmod.common.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tk.patsite.warmod.common.Warmod;

@Mixin(TitleScreen.class)
public abstract class TitleScreenCustomText extends Screen {
    protected TitleScreenCustomText(Text title) {
        super(title);
    }

    // https://discord.com/channels/507304429255393322/807617700734042122/1008063004044177519
    @ModifyVariable(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V",
            ordinal = 5,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawStringWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V")
    )
    private int fadeIndexHandler(int fadeIndex, MatrixStack matrices, int mouseX, int mouseY, float delta) {
        String text = I18n.translate("menu.warmod", Warmod.VERSION);
        if(Warmod.IS_DEV_ENV)
            text += I18n.translate("menu.warmod.devenv");
        drawStringWithShadow(matrices, this.textRenderer, text, 2, this.height - 20, 16777215 | fadeIndex);
        return fadeIndex;
    }
}
