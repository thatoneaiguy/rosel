package com.thatoneaiguy.bismuthimite.init;

import com.thatoneaiguy.bismuthimite.Bismuthimite;
import com.thatoneaiguy.bismuthimite.block.entity.KilnBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class BismuthimiteBlockEntities {

	public static BlockEntityType<KilnBlockEntity> KILN;

	public static void register() {
		KILN = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Bismuthimite.MODID, "kiln"),
			QuiltBlockEntityTypeBuilder.create(KilnBlockEntity::new,
				BismuthimiteBlocks.KILN).build(null));
	}

}
