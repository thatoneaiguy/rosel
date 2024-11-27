package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.cca.KopisComponent;
import com.thatoneaiguy.rosel.event.KeyInputHandler;
import com.thatoneaiguy.rosel.hud.KopisHudOverlay;
import com.thatoneaiguy.rosel.init.RoselItems;
import com.thatoneaiguy.rosel.init.RoselLodestoneParticles;
import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import com.thatoneaiguy.rosel.packet.ShockwavePacket;
import com.thatoneaiguy.rosel.particle.RoselDrip;
import com.thatoneaiguy.rosel.particle.ShockwaveParticle;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class RoselClient implements ClientModInitializer {

	private static MinecraftClient client;

	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("rosel", "disc"), "main");

	public static DefaultParticleType DRIPPING_ROSEL_DROP;
	public static DefaultParticleType FALLING_ROSEL_DROP;
	public static DefaultParticleType LANDING_ROSEL_DROP;

	public static DefaultParticleType ROSEL_CROSS_V;
	public static DefaultParticleType ROSEL_BOLT_V;

	public static DefaultParticleType SHOCKWAVE;

	public static void registerModelPredicateProviders() {
		ModelPredicateProviderRegistry.register(RoselItems.ROSEL_GAUNTLET, new Identifier("mode"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null) {
				return 0.0F;
			}
			return (float) RoselGauntletItem.getMode(itemStack);
		});
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
		RoselMessages.registerS2C();
		//EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);
		//EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("rosel", "rosel_kopis_gui", "inventory")));

		RoselLodestoneParticles.initFactories();

		DRIPPING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:dripping_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(DRIPPING_ROSEL_DROP, RoselDrip.DrippingFollyRedPaintDropFactory::new);
		FALLING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:falling_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(FALLING_ROSEL_DROP, RoselDrip.FallingFollyRedPaintDropFactory::new);
		LANDING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "rosel:landing_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(LANDING_ROSEL_DROP, RoselDrip.LandingFollyRedPaintDropFactory::new);

		ROSEL_CROSS_V = Registry.register(Registry.PARTICLE_TYPE, "rosel:rosel_cross_v", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(ROSEL_CROSS_V, FlameParticle.Factory::new);
		ROSEL_BOLT_V = Registry.register(Registry.PARTICLE_TYPE, "rosel:rosel_bolt_v", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(ROSEL_BOLT_V, FlameParticle.Factory::new);

		SHOCKWAVE = Registry.register(Registry.PARTICLE_TYPE, "rosel:shockwave", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(SHOCKWAVE, ShockwaveParticle.Factory::new);

		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("rosel", "particle/rosel_cross"));
			registry.register(new Identifier("rosel", "particle/rosel_bolt"));
		}));

		registerModelPredicateProviders();

		ClientPlayNetworking.registerGlobalReceiver(ShockwavePacket.ID, ((client, handler, buf, responseSender) -> {
			Vec3d position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
			client.execute(() -> {
				ClientPlayerEntity player = client.player;
				if (player != null) {
					double distance = player.getPos().distanceTo(position);
				}

			});
		}));

		HudRenderCallback.EVENT.register(new KopisHudOverlay());
	}
}

