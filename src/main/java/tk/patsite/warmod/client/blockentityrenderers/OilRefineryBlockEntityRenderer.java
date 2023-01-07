package tk.patsite.warmod.client.blockentityrenderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import tk.patsite.warmod.common.blocks.entites.OilRefineryBlockEntity;

public class OilRefineryBlockEntityRenderer implements BlockEntityRenderer<OilRefineryBlockEntity> {
    public OilRefineryBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(OilRefineryBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();



        matrices.pop();
    }
}
