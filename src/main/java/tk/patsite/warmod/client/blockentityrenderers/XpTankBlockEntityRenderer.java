package tk.patsite.warmod.client.blockentityrenderers;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3f;
import tk.patsite.warmod.common.blocks.entites.XpTankBlockEntity;

public class XpTankBlockEntityRenderer implements BlockEntityRenderer<XpTankBlockEntity> {
    private final TextRenderer textRenderer;

    public XpTankBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        textRenderer = ctx.getTextRenderer();
    }

    @Override
    public void render(XpTankBlockEntity xpTankBlockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        String text = xpTankBlockEntity.getLevelAsString();

        PlayerMoveC2SPacket


        matrices.translate(0.5, 1.0625f + 0.00001, 0.45);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
        matrices.scale(0.02f, 0.02f, 0.02f);


        // TODO: sync data with client to draw level
        textRenderer.draw(text, -textRenderer.getWidth(text) / 2f, 0, 0xffffff, false, matrices.peek().getPositionMatrix(), vertexConsumers, false, 0x000000, light);

        matrices.pop();
    }
}
