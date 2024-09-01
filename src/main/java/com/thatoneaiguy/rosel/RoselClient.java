package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.event.KeyInputHandler;
import com.thatoneaiguy.rosel.init.RoselEntities;
import com.thatoneaiguy.rosel.init.RoselFluids;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import com.thatoneaiguy.rosel.render.DiscModel;
import com.thatoneaiguy.rosel.render.DiscRenderer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class RoselClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_DISC_LAYER = new EntityModelLayer(new Identifier("rosel", "disc"), "main");

	public static DefaultParticleType DRIPPING_PASSIVE_ROSEL_DRIP;
	public static DefaultParticleType FALLING_PASSIVE_ROSEL_DRIP;
	public static DefaultParticleType LANDING_FOLLY_RED_PAINT_DROP;

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
		RoselMessages.registerS2C();
		EntityRendererRegistry.register(RoselEntities.DiscEntityType, DiscRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_DISC_LAYER, DiscModel::getTexturedModelData);
		ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("rosel", "rosel_kapis_gui", "inventory")));

		FluidRenderHandlerRegistry.INSTANCE.register(RoselFluids.STILL_PURE_ROSEL.getStill(), RoselFluids.FLOWING_PURE_ROSEL,
			new SimpleFluidRenderHandler(
				new Identifier("minecraft:block/water_still"),
				new Identifier("minecraft:block/water_flow"),
				0xA1E038D0 // 0x ARGB
			));

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
			RoselFluids.STILL_PURE_ROSEL, RoselFluids.FLOWING_PURE_ROSEL);

		/*DRIPPING_PASSIVE_ROSEL_DRIP = Registry.register(Registry.PARTICLE_TYPE, "blast:dripping_folly_red_paint_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(DRIPPING_PASSIVE_ROSEL_DRIP, PassiveRoselDripParticle.DrippingPassiveRoselDripFactory::new);
		FALLING_PASSIVE_ROSEL_DRIP = Registry.register(Registry.PARTICLE_TYPE, "blast:falling_folly_red_paint_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(FALLING_PASSIVE_ROSEL_DRIP, PassiveRoselDripParticle.FallingPassiveRoselDripFactory::new);
		LANDING_FOLLY_RED_PAINT_DROP = Registry.register(Registry.PARTICLE_TYPE, "blast:landing_folly_red_paint_drop", FabricParticleTypes.simple(true));
		ParticleFactoryRegistry.getInstance().register(LANDING_FOLLY_RED_PAINT_DROP, PassiveRoselDripParticle.LandingPassiveRoselDripFactory::new);*/
	}
}
