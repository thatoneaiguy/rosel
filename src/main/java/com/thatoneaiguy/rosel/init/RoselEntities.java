package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.entity.DiscEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RoselEntities {
	public static final EntityType<DiscEntity> DiscEntityType = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(Rosel.MODID, "disc"),
		FabricEntityTypeBuilder.<DiscEntity>create(SpawnGroup.MISC, DiscEntity::new)
			.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
			.fireImmune()
			.trackRangeBlocks(4).trackedUpdateRate(10)
			.build());

	public static void register() {

	}
}
