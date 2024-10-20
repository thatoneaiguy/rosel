package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.cca.RoselCoatingComponent;
import com.thatoneaiguy.rosel.init.*;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rosel implements ModInitializer, EntityComponentInitializer {
	public static final String MODID = "rosel";
	public static int ticks = -1;


	public static final Logger LOGGER = LoggerFactory.getLogger("Rosel");
	private static <T extends
		Entity> EntityType< T > registerEntityType(String name, SpawnGroup group, EntityType.EntityFactory < T > entityFactory,
												   float width, float height){
		Identifier entityId = new Identifier(MODID, name);
		QuiltEntityTypeBuilder<T> entityTypeBuilder = QuiltEntityTypeBuilder.create(group, entityFactory)
			.setDimensions(EntityDimensions.fixed(width, height))
			.maxBlockTrackingRange(4)
			.trackingTickInterval(10);
		return Registry.register(Registry.ENTITY_TYPE, entityId, entityTypeBuilder.build());
	}

	//public static final EntityType<DiscItem.> GrenadeProjectileEntityType = registerEntityType("grenade", SpawnGroup.MISC, ExplosiveItem.GrenadeEntity::new, 1F, 2F);

	// CCA
	public static final ComponentKey<RoselCoatingComponent> ROSEL_COATING_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_coating"), RoselCoatingComponent.class);
	//public static final ComponentKey<RoselParryingComponent> ROSEL_PARRY_COMPONENT =
	//	ComponentRegistry.getOrCreate(id("rosel_parry"), RoselParryingComponent.class);

	@Override
	public void onInitialize(ModContainer mod) {
		RoselItems.register();
		RoselItemGroup.register();
		RoselBlocks.registerAll();
		// Block Entities
		//Loot table modifiers
		RoselSounds.register();
		//Block Entities
		//RoselEntities.register();
		RoselLodestoneParticles.init();
		//effects-|
		//potions-|
		// Fluid
		//entity attributes registry

		RoselEnchantments.register();
		RoselDamageSources.register();

		RoselMessages.registerC2S();

		MidnightConfig.init("rosel", RoselConfig.class);

		ServerTickEvents.END.register(server -> {
			if ( ticks > 0 ) {
				ticks--;
			} else if ( ticks == 0 ) {
				ticks= -1;
			}
		});
	}

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(PlayerEntity.class, ROSEL_COATING_COMPONENT).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(RoselCoatingComponent::new);
		//registry.beginRegistration(PlayerEntity.class, ROSEL_PARRY_COMPONENT).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(RoselParryingComponent::new);
	}

	public static Identifier id(String name) {
		return new Identifier("rosel", name);
	}

}
