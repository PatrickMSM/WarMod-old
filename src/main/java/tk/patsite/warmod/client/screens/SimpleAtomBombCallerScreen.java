package tk.patsite.warmod.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.registry.WarmodNetworking;

public class SimpleAtomBombCallerScreen extends Screen {
    private static final Identifier TEXTURE = new Identifier(Warmod.MODID, "textures/gui/simpleatombombcaller.png");
    private static final int TEXTURE_WIDTH = 118;
    private static final int TEXTURE_HEIGHT = 92;

    public SimpleAtomBombCallerScreen() {
        super(Text.translatable("gui.warmod.simpleatombombcaller"));
    }

    @Override
    public void init() {
        TextFieldWidget xField = new TextFieldWidget(textRenderer, (width-40)/2-25, height/2+20, 40, 15, Text.empty());
        TextFieldWidget zField = new TextFieldWidget(textRenderer, (width-40)/2+25, height/2+20, 40, 15, Text.empty());
        addDrawableChild(xField);
        addDrawableChild(zField);
        addDrawableChild(new ButtonWidget((width-100)/2, height/2-15, 100, 20, Text.translatable("gui.warmod.simpleatombombcaller.here"), (button) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (player == null)
                return;

            xField.setText(String.valueOf(player.getX()));
            zField.setText(String.valueOf(player.getZ()));
        }));
        addDrawableChild(new ButtonWidget((width-100)/2, height/2-40, 100, 20, Text.translatable("gui.warmod.simpleatombombcaller.launch"), (button) -> {
            // validate x and y
            double x, z;
            try {
                x = Double.parseDouble(xField.getText());
                z = Double.parseDouble(zField.getText());
            } catch (NumberFormatException ignored) {
                // do nothing
                return;
            }
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeDouble(x);
            buf.writeDouble(z);
            ClientPlayNetworking.send(WarmodNetworking.SIMPLE_ATOM_BOMB_CALLER_CHANNEL, buf);
            close();
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        drawCenteredTextWithShadow(matrices, textRenderer, Text.literal("X:").asOrderedText(), (width-40)/2-25+20, height/2+10, 0xFFFFFF);
        drawCenteredTextWithShadow(matrices, textRenderer, Text.literal("Z:").asOrderedText(), (width-40)/2+25+20, height/2+10, 0xFFFFFF);
    }

    private void drawBackground(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        drawTexture(matrices, (width-TEXTURE_WIDTH)/2, (height-TEXTURE_HEIGHT)/2, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }
}
