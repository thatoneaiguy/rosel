package com.thatoneaiguy.rosel;

import com.mojang.blaze3d.shader.GlslImportProcessor;
import com.thatoneaiguy.rosel.init.*;
import com.thatoneaiguy.rosel.init.RoselItemGroup;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rosel implements ModInitializer {
	public static final String MODID = "rosel";
	public static final Logger LOGGER = LoggerFactory.getLogger("Rosel");
	private static <T extends
		Entity> EntityType< T > registerEntityType(String name, SpawnGroup group, EntityType.EntityFactory < T > entityFactory,
												   float width, float height){
		Identifier entityId = new Identifier(MODID, name);
		FabricEntityTypeBuilder<T> entityTypeBuilder = FabricEntityTypeBuilder.create(group, entityFactory)
			.dimensions(EntityDimensions.fixed(width, height))
			.trackRangeBlocks(4).trackedUpdateRate(10);
		return Registry.register(Registry.ENTITY_TYPE, entityId, entityTypeBuilder.build());
	}

	//public static final EntityType<DiscItem.> GrenadeProjectileEntityType = registerEntityType("grenade", SpawnGroup.MISC, ExplosiveItem.GrenadeEntity::new, 1F, 2F);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("fuck i forgot valentines day");
		LOGGER.info("GO FUCK YOUR SELF :)");
		RoselItems.register();
		RoselItemGroup.register();
		RoselBlocks.registerAll();
		// Block Entities
		//Loot table modifiers
		//Sounds
		//Block Entities
		//RoselEntities.register();
		//Particles
		//effects-|
		//potions-|
		// Fluid
		//entity attributes registry

		RoselMessages.registerC2S();
	}
}
