package tk.patsite.warmod.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.screenhandlers.OilRefineryScreenHandler;

public class OilRefineryScreen extends HandledScreen<OilRefineryScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Warmod.MODID, "textures/gui/container/oil_refinery.png");
    private final OilRefineryScreenHandler screenHandler;

    public OilRefineryScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super((OilRefineryScreenHandler) handler, inventory, title);
        screenHandler = (OilRefineryScreenHandler) handler;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        this.drawTexture(matrices, x + 79, y + 34, 176, 0, screenHandler.getCookProgress() + 1, 16);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

        // TODO: add a "mode" selector button for refinery to select fuel, plastic, etc
        //addDrawableChild(CyclingButtonWidget.builder());

    }
}

