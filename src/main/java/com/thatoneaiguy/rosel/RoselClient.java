package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.init.RoselEntities;
import com.thatoneaiguy.rosel.render.DiscModel;
import com.thatoneaiguy.rosel.render.DiscRenderer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class RoselClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("rosel", "disc"), "main");

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);

		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("rosel", "rosel_kapis_gui", "inventory")));
	}
}
