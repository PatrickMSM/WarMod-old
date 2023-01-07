package tk.patsite.warmod.client.renderers;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.client.WarmodClient;
import tk.patsite.warmod.client.models.SimpleAtomBombEntityModel;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.entities.SimpleAtomBombEntity;

public class SimpleAtomBombEntityRenderer extends EntityRenderer<SimpleAtomBombEntity> {
    private final SimpleAtomBombEntityModel model;
    private static final Identifier TEXTURE = new Identifier(Warmod.MODID, "textures/entity/simple_atom_bomb/simple_atom_bomb.png");


    public SimpleAtomBombEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new SimpleAtomBombEntityModel(context.getPart(WarmodClient.MODEL_SIMPLE_ATOM_BOMB_LAYER));
    }

    @Override
    public void render(SimpleAtomBombEntity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        matrixStack.push();

        // rotate model https://discord.com/channels/507304429255393322/507982478276034570/993782885083529236
        // seems like its already rotated

        VertexConsumer consumer =  vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE));
        model.render(matrixStack, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F,1.0F, 1.0F);

        new Camera();


        matrixStack.pop();

        //super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }

    @Override
    public Identifier getTexture(SimpleAtomBombEntity entity) {
        return TEXTURE;
    }
}
