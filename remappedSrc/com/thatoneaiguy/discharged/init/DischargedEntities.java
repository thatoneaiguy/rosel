package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.entity.RoselCrystalPlayerShapeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DischargedEntities {
//	public static final EntityType<DiscEntity> DiscEntityType = Registry.register(
//		Registry.ENTITY_TYPE,
//		new Identifier(Rosel.MODID, "disc"),
//		FabricEntityTypeBuilder.<DiscEntity>create(SpawnGroup.MISC, DiscEntity::new)
//			.spawnGroup(SpawnGroup.MISC)
//			.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
//			.fireImmune()
//			.trackRangeBlocks(4).trackedUpdateRate(10)
//			.build());
	public static final EntityType<RoselCrystalPlayerShapeEntity> ROSEL_CRYSTAL_PLAYER_SHAPE_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(Discharged.MODID, "disc"),
		FabricEntityTypeBuilder.<RoselCrystalPlayerShapeEntity>create(SpawnGroup.MISC, RoselCrystalPlayerShapeEntity::new)
			.spawnGroup(SpawnGroup.MISC)
			.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
			.fireImmune()
			.trackRangeBlocks(4).trackedUpdateRate(10)
			.build());

	public static void register() {

	}
}
