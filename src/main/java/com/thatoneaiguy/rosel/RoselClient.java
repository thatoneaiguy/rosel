package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.event.KeyInputHandler;
import com.thatoneaiguy.rosel.hud.KapisHudOverlay;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import com.thatoneaiguy.rosel.particle.RoselDrip;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class RoselClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("rosel", "disc"), "main");

	public static DefaultParticleType DRIPPING_ROSEL_DROP;
	public static DefaultParticleType FALLING_ROSEL_DROP;
	public static DefaultParticleType LANDING_ROSEL_DROP;

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
		RoselMessages.registerS2C();
		//EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);
		//EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("rosel", "rosel_kapis_gui", "inventory")));

		DRIPPING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:dripping_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(DRIPPING_ROSEL_DROP, RoselDrip.DrippingFollyRedPaintDropFactory::new);
		FALLING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:falling_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(FALLING_ROSEL_DROP, RoselDrip.FallingFollyRedPaintDropFactory::new);
		LANDING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:landing_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(LANDING_ROSEL_DROP, RoselDrip.LandingFollyRedPaintDropFactory::new);

		HudRenderCallback.EVENT.register(new KapisHudOverlay());
	}
}

