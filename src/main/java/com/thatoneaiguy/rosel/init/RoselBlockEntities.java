package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.block.entity.RoselBlockEntity;
import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.block.entity.BasinBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RoselBlockEntities {

	public static BlockEntityType<BasinBlockEntity> BASIN;

	public static BlockEntityType<RoselBlockEntity> ROSEL_BLOCK;

	public static void register() {
		BASIN = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Rosel.MODID, "basin"),
			FabricBlockEntityTypeBuilder.create(BasinBlockEntity::new,
				RoselBlocks.BASIN).build(null));
		ROSEL_BLOCK = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Rosel.MODID, "rosel_block"),
			FabricBlockEntityTypeBuilder.create(RoselBlockEntity::new,
				RoselBlocks.ROSEL_BLOCK).build(null));
	}

}
