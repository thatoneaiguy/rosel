package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.block.RawRoselBlock;
import com.thatoneaiguy.discharged.block.RoughRosel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DischargedBlocks {

	public static final Block RAW_ROSEL = new RawRoselBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
	public static final Block ROUGH_ROSEL = new RoughRosel(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE));
	public static final Block PERFECT_ROSEL = new Block(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE));

	static Map<String, Object> BLOCKS = Stream.of(new Object[][] {
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
		Registry.register(Registry.BLOCK, new Identifier(Discharged.MODID, name), block);
	}

	private static void registerBlockItem(String name, Block block) {
		Registry.register(Registry.ITEM, new Identifier(Discharged.MODID, name),
			new BlockItem(block, new FabricItemSettings()));
	}

	private static void registerBlockWithoutBlockItem(String name, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(Discharged.MODID, name), block);
	}

	private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
		return (state) -> {
			return (Boolean)state.get(Properties.LIT) ? litLevel : 0;
		};
	}
}
