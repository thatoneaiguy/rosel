package com.thatoneaiguy.discharged.init;

import com.sammy.lodestone.systems.rendering.particle.type.LodestoneParticleType;
import com.thatoneaiguy.discharged.Discharged;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.function.BiConsumer;

public interface  DischargedLodestoneParticles {
	LodestoneParticleType ROSEL_CROSS = new LodestoneParticleType();
	LodestoneParticleType ROSEL_BOLT = new LodestoneParticleType();

	static void init() {
		initParticles(bind(Registry.PARTICLE_TYPE));
	}

	@ClientOnly
	static void initFactories() {
		ParticleFactoryRegistry.getInstance().register(ROSEL_CROSS, LodestoneParticleType.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ROSEL_BOLT, LodestoneParticleType.Factory::new);
	}

	private static void initParticles(BiConsumer<ParticleType<?>, Identifier> registry) {
		registry.accept(ROSEL_CROSS, new Identifier(Discharged.MODID, "rosel_cross"));
		registry.accept(ROSEL_BOLT, new Identifier(Discharged.MODID, "rosel_bolt"));
	}

	private static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
		return (t, id) -> Registry.register(registry, id, t);
	}
}
