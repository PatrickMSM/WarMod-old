package tk.patsite.warmod.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

// cleaned up & modified code
public class SimpleAtomBombEntityModel extends EntityModel<Entity> {
	private final ModelPart base;

	public SimpleAtomBombEntityModel(ModelPart root) {
		this.base = root.getChild("main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData mainBase = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -21.0F, -4.0F, 8.0F, 21.0F, 8.0F, new Dilation(0.01F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		mainBase.addChild("cube_r1", ModelPartBuilder.create().uv(24, 0).cuboid(-9.0F, -4.0F, -1.0F, 18.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		mainBase.addChild("cube_r2", ModelPartBuilder.create().uv(0, 29).cuboid(-9.0F, -4.0F, -1.0F, 18.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}