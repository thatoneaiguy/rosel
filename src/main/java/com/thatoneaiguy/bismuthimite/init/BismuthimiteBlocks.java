package com.thatoneaiguy.bismuthimite.init;

import com.thatoneaiguy.bismuthimite.Bismuthimite;
import com.thatoneaiguy.bismuthimite.block.Kiln;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BismuthimiteBlocks {

	public static final Block KILN = registerBlock("kiln",
		new Kiln(FabricBlockSettings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)), BismuthimiteItems.BISMUTHIMITE);

	private static Block registerBlock(String name, Block block, ItemGroup tab) {
		registerBlockItem(name, block, tab);
		return Registry.register(Registry.BLOCK, new Identifier(Bismuthimite.MODID, name), block);
	}

	private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
		return Registry.register(Registry.ITEM, new Identifier(Bismuthimite.MODID, name),
			new BlockItem(block, new FabricItemSettings().group(tab)));
	}

	public static void register() {
	}

}
