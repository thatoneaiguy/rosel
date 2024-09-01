package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.fluid.PureRosel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RoselFluids {
	public static FlowableFluid STILL_PURE_ROSEL;
	public static FlowableFluid FLOWING_PURE_ROSEL;
	public static Block PURE_ROSEL_BLOCK;

	public static void register() {
		STILL_PURE_ROSEL = Registry.register(Registry.FLUID,
			new Identifier(Rosel.MODID, "pure_rosel"), new PureRosel.Still());
		FLOWING_PURE_ROSEL = Registry.register(Registry.FLUID,
			new Identifier(Rosel.MODID, "flowing_pure_rosel"), new PureRosel.Flowing());

		PURE_ROSEL_BLOCK = Registry.register(Registry.BLOCK, "pure_rosel_block",
			new FluidBlock(RoselFluids.FLOWING_PURE_ROSEL, FabricBlockSettings.copyOf(Blocks.WATER)){ });

	}
}
