package tk.patsite.warmod.client.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.common.entities.CameraEntity;

public class CameraEntityRenderer extends EntityRenderer<CameraEntity> {
    public CameraEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(CameraEntity entity) {
        return null;
    }

    @Override
    public void render(CameraEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
    }
}