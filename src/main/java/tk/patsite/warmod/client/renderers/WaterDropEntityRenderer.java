package tk.patsite.warmod.client.renderers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import tk.patsite.warmod.client.WarmodClient;
import tk.patsite.warmod.common.entities.WaterDropEntity;

public class WaterDropEntityRenderer extends EntityRenderer<WaterDropEntity> {
    private static final int DETAIL = 25;
    private static final float TRIANGLE_ANGLE = MathHelper.TAU / DETAIL;

    public WaterDropEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(WaterDropEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient.getInstance().getProfiler().push("renderWaterDrop");
        matrices.push();

        VertexConsumer vertices = vertexConsumers.getBuffer(WarmodClient.CIRCLE_LAYER);
        Matrix4f posMatrix = matrices.peek().getPositionMatrix();

        matrices.translate(0.125D, 0.125D, 0);
        matrices.multiply(this.dispatcher.getRotation());

        for (int i = 1; i <= DETAIL; i++) {
            float angle2 = TRIANGLE_ANGLE * (i+1);
            float angle3 = TRIANGLE_ANGLE * (i+2);
            vertices.vertex(posMatrix, 0, 0, 0).color(0.0f, 0.1f, 0.5f, 0.4f).light(light).next();
            vertices.vertex(posMatrix, MathHelper.cos(angle2) * 0.25f, MathHelper.sin(angle2) * 0.25f, 0).color(0.0f, 0.1f, 0.5f, 0.4f).light(light).next();
            vertices.vertex(posMatrix, MathHelper.cos(angle3) * 0.25f, MathHelper.sin(angle3) * 0.25f, 0).color(0.0f, 0.1f, 0.5f, 0.4f).light(light).next();
        }

        matrices.pop();
        MinecraftClient.getInstance().getProfiler().pop();
    }


    @Override
    public Identifier getTexture(WaterDropEntity entity) {
        return null;
    }
}
