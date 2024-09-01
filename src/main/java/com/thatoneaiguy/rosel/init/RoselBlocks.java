package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.block.Basin;
import com.thatoneaiguy.rosel.block.RawRoselBlock;
import com.thatoneaiguy.rosel.block.RoughRosel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoselBlocks {

	public static final Block BASIN = new Basin(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE));
	public static final Block RAW_ROSEL = new RawRoselBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
	public static final Block ROUGH_ROSEL = new RoughRosel(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE));
	public static final Block PERFECT_ROSEL = new Block(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE));

	static Map<String, Object> BLOCKS = Stream.of(new Object[][] {
		{"basin", BASIN},
		{"raw_rosel", RAW_ROSEL},
		{"rough_rosel", ROUGH_ROSEL},
		{"perfect_rosel", PERFECT_ROSEL},
	}).collect(Collectors.toMap(entry -> (String) entry[0], entry -> entry[1]));

	public static void registerAll() {
		for (Map.Entry<String, Object> entry : BLOCKS.entrySet()) {
			String key = entry.getKey();
			Block value = (Block) entry.getValue();

			registerBlock(key, value);
		}
	}

	private static void registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		Registry.register(Registry.BLOCK, new Identifier(Rosel.MODID, name), block);
	}

	private static void registerBlockItem(String name, Block block) {
		Registry.register(Registry.ITEM, new Identifier(Rosel.MODID, name),
			new BlockItem(block, new FabricItemSettings()));
	}

	private static void registerBlockWithoutBlockItem(String name, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(Rosel.MODID, name), block);
	}
}
