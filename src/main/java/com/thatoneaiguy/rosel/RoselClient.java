package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.event.KeyInputHandler;
import com.thatoneaiguy.rosel.hud.KopisHudOverlay;
import com.thatoneaiguy.rosel.init.RoselParticles;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import com.thatoneaiguy.rosel.particle.RoselCross;
import com.thatoneaiguy.rosel.particle.RoselDrip;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class RoselClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("rosel", "disc"), "main");

	public static DefaultParticleType DRIPPING_ROSEL_DROP;
	public static DefaultParticleType FALLING_ROSEL_DROP;
	public static DefaultParticleType LANDING_ROSEL_DROP;

	public static DefaultParticleType ROSEL_CROSS_V;

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
		RoselMessages.registerS2C();
		//EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);
		//EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("rosel", "rosel_kopis_gui", "inventory")));

		RoselParticles.initFactories();

		DRIPPING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:dripping_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(DRIPPING_ROSEL_DROP, RoselDrip.DrippingFollyRedPaintDropFactory::new);
		FALLING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:falling_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(FALLING_ROSEL_DROP, RoselDrip.FallingFollyRedPaintDropFactory::new);
		LANDING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:landing_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(LANDING_ROSEL_DROP, RoselDrip.LandingFollyRedPaintDropFactory::new);

		ROSEL_CROSS_V = Registry.register(Registry.PARTICLE_TYPE, "rosel:rosel_cross_v", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(ROSEL_CROSS_V, FlameParticle.Factory::new);

		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("rosel", "particle/rosel_cross"));
		}));

		HudRenderCallback.EVENT.register(new KopisHudOverlay());
	}
}

