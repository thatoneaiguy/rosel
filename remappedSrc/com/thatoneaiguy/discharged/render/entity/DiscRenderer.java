package com.thatoneaiguy.discharged.render.entity;

/*
@Environment(EnvType.CLIENT)
public class DiscRenderer extends EntityRenderer<DiscEntity> {
	public static final Identifier TEXTURE = new Identifier(Rosel.MODID, "textures/entity/tets.png");
	//private final DiscModel model;

	public DiscRenderer(EntityRendererFactory.Context context) {
		super(context);
		//this.model = new DiscModel(context.getPart(RoselClient.MODEL_DISC_LAYER));
	}

	@Override
	public Identifier getTexture(DiscEntity entity) {
		return TEXTURE;
	}

	*/
/*public void render(DiscEntity discEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, discEntity.prevYaw, discEntity.getYaw()) - 90.0F));
		matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, discEntity.prevPitch, discEntity.getPitch()) + 90.0F));
		VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(discEntity)), false, discEntity.isEnchanted());
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(discEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	public Identifier getTexture(DiscEntity discEntity) {
		return TEXTURE;
	}*//*

}
*/
