package com.thatoneaiguy.discharged.render;

import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GauntletItemRenderer extends GeoItemRenderer<RoselGauntletItem> {
	private ThreadLocal<Identifier> currentTexture = new ThreadLocal<>();

	public GauntletItemRenderer() {
		super(new GauntletItemModel());
	}

	@Override
	public void render(RoselGauntletItem animatable, MatrixStack matrices, VertexConsumerProvider bufferSource, int packedLight, ItemStack stack) {
		GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelResource(animatable));
		matrices.push();
		matrices.translate(0.5f, 0.51f, 0.5f);

		Identifier gauntletTexture = RoselGauntletItem.getModeTexture(stack);

		currentTexture.set(gauntletTexture);

		MinecraftClient.getInstance().getTextureManager().bindTexture(gauntletTexture);
		RenderLayer renderLayer = RenderLayer.getEntityCutout(gauntletTexture);

		this.render(model, animatable, 0.0F, renderLayer, matrices, bufferSource, bufferSource.getBuffer(renderLayer), packedLight, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

	//	public GauntletItemRenderer(TextureManager manager, BakedModelManager bakery, ItemColors colors, BuiltinModelItemRenderer builtinModelItemRenderer) {
//		super(manager, bakery, colors, builtinModelItemRenderer);
//	}
//
//	@Override
//	public void renderItem(ItemStack stack, ModelTransformation.Mode transformationType, int light, int overlay, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int seed) {
//		MinecraftClient client = MinecraftClient.getInstance();
//		RoselGauntletItem.GauntletMode mode = getItemMode(stack);
//
//		switch (mode) {
//			case DEFLECT:
//				client.getTextureManager().bindTexture(new Identifier("modid", "textures/items/multifunction_item_damage_reflect.png"));
//				break;
//			case SHOCKWAVE:
//				client.getTextureManager().bindTexture(new Identifier("modid", "textures/items/multifunction_item_projectile_reflect.png"));
//				break;
//			case CONDUCTOR:
//				client.getTextureManager().bindTexture(new Identifier("modid", "textures/items/multifunction_item_damage_deal.png"));
//				break;
//		}
//
//		super.renderItem(stack, transformationType, light, overlay, matrices, vertexConsumers, seed);
//	}
//
//	private RoselGauntletItem.GauntletMode getItemMode(ItemStack stack) {
//		// Retrieve the mode from NBT or item data
//		return RoselGauntletItem.GauntletMode.values()[stack.getOrCreateNbt().getInt("Mode")];
//	}
}
