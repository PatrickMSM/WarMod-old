package tk.patsite.warmod.client.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.entities.FastMinecartEntity;

public class FastMinecartEntityRenderer extends MinecartEntityRenderer<FastMinecartEntity> {
    private static final Identifier TEXTURE = new Identifier(Warmod.MODID, "textures/entity/fastminecart/fastminecart.png");
    private static final EntityModelLayer LAYER = new EntityModelLayer(new Identifier("minecraft", "minecart"), "main");

    public FastMinecartEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, LAYER);
    }

    @Override
    public Identifier getTexture(FastMinecartEntity abstractMinecartEntity) {
        return TEXTURE;
    }
}
