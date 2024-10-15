package com.thatoneaiguy.rosel.init;

import com.sammy.lodestone.systems.rendering.particle.type.LodestoneParticleType;
import com.thatoneaiguy.rosel.Rosel;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.function.BiConsumer;

public interface RoselParticles {
	LodestoneParticleType ROSEL_CROSS = new LodestoneParticleType();

	static void init() {
		initParticles(bind(Registry.PARTICLE_TYPE));
	}

	@ClientOnly
	static void initFactories() {
		ParticleFactoryRegistry.getInstance().register(ROSEL_CROSS, LodestoneParticleType.Factory::new);
	}

	private static void initParticles(BiConsumer<ParticleType<?>, Identifier> registry) {
		registry.accept(ROSEL_CROSS, new Identifier(Rosel.MODID, "rosel_cross"));
	}

	private static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
		return (t, id) -> Registry.register(registry, id, t);
	}
}
