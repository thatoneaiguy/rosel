package com.thatoneaiguy.discharged;

import com.thatoneaiguy.discharged.event.KeyInputHandler;
import com.thatoneaiguy.discharged.hud.KopisHudOverlay;
import com.thatoneaiguy.discharged.init.DischargedBlocks;
import com.thatoneaiguy.discharged.init.DischargedItems;
import com.thatoneaiguy.discharged.init.DischargedLodestoneParticles;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import com.thatoneaiguy.discharged.networking.RoselMessages;
import com.thatoneaiguy.discharged.packet.ShockwavePacket;
import com.thatoneaiguy.discharged.particle.RoselDrip;
import com.thatoneaiguy.discharged.particle.ShockwaveParticle;
import com.thatoneaiguy.discharged.render.GauntletItemRenderer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registry;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DischargedClient implements ClientModInitializer {

	private static MinecraftClient client;

	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("discharged", "disc"), "main");

	public static DefaultParticleType DRIPPING_ROSEL_DROP;
	public static DefaultParticleType FALLING_ROSEL_DROP;
	public static DefaultParticleType LANDING_ROSEL_DROP;

	public static DefaultParticleType ROSEL_CROSS_V;
	public static DefaultParticleType ROSEL_BOLT_V;

	public static DefaultParticleType SHOCKWAVE;

//	public void registerModelPredicateProviders() {
//		ModelPredicateProviderRegistry.register(DischargedItems.ROSEL_GAUNTLET, new Identifier("mode"), (itemStack, clientWorld, livingEntity, seed) -> {
//			if (livingEntity == null) {
//				return 0.0F;
//			}
//			return (float) RoselGauntletItem.getPredicate();
//		});
//	}


	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
		RoselMessages.registerS2C();
		//EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);
		//EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("discharged", "rosel_kopis_gui", "inventory")));
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("discharged", "rosel_gauntlet_gui", "inventory")));

		DischargedLodestoneParticles.initFactories();
		GeoItemRenderer.registerItemRenderer(DischargedItems.ROSEL_GAUNTLET, new GauntletItemRenderer());

		DRIPPING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "discharged:dripping_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(DRIPPING_ROSEL_DROP, RoselDrip.DrippingFollyRedPaintDropFactory::new);
		FALLING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "discharged:falling_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(FALLING_ROSEL_DROP, RoselDrip.FallingFollyRedPaintDropFactory::new);
		LANDING_ROSEL_DROP = Registry.register(Registry.PARTICLE_TYPE, "discharged:landing_rosel_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(LANDING_ROSEL_DROP, RoselDrip.LandingFollyRedPaintDropFactory::new);

		ROSEL_CROSS_V = Registry.register(Registry.PARTICLE_TYPE, "discharged:rosel_cross_v", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(ROSEL_CROSS_V, FlameParticle.Factory::new);
		ROSEL_BOLT_V = Registry.register(Registry.PARTICLE_TYPE, "discharged:rosel_bolt_v", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(ROSEL_BOLT_V, FlameParticle.Factory::new);

		SHOCKWAVE = Registry.register(Registry.PARTICLE_TYPE, "discharged:shockwave", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(SHOCKWAVE, ShockwaveParticle.Factory::new);

		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("discharged", "particle/rosel_cross"));
			registry.register(new Identifier("discharged", "particle/rosel_bolt"));
		}));

		//registerModelPredicateProviders();

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

