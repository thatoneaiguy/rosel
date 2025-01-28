package com.thatoneaiguy.discharged.mixin;

import com.thatoneaiguy.discharged.init.DischargedItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
	@Unique private static final ModelIdentifier ROSEL_KAPIS_GUI = new ModelIdentifier("discharged:rosel_kopis_gui#inventory");
	@Unique private static final ModelIdentifier ROSEL_GAUNTLET_GUI = new ModelIdentifier("discharged:rosel_gauntlet_gui#inventory");

	@Shadow private @Final ItemModels models;

	@ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"), argsOnly = true)
	private BakedModel rosel$renderGuiModels(BakedModel model, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel modelAgain) {
		boolean bl = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND;
		if (bl) {
			if (stack.isOf(DischargedItems.ROSEL_KOPIS)) {
				return models.getModelManager().getModel(ROSEL_KAPIS_GUI);
			}
		}
		if (bl) {
			if (stack.isOf(DischargedItems.ROSEL_GAUNTLET)) {
				return models.getModelManager().getModel(ROSEL_GAUNTLET_GUI);
			}
		}
		return model;
	}
}
