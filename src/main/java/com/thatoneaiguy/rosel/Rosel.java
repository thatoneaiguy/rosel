package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.init.*;
import com.thatoneaiguy.rosel.item.DiscItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rosel implements ModInitializer {

	public static final String MODID = "rosel";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

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

		RoselBlocks.register();

		RoselBlockEntities.register();

		RoselEntities.register();

		RoselItems.register();

	}
}
