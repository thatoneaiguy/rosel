package com.thatoneaiguy.rosel.block.entity;

import com.thatoneaiguy.rosel.init.RoselBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RoselBlockEntity extends BlockEntity {
	static int AGE = 0;
	static boolean crafting = false;

	public RoselBlockEntity(BlockPos pos, BlockState state) {
		super(RoselBlockEntities.ROSEL_BLOCK, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, RoselBlockEntity entity) {
		double random = Math.random();

		if (random <= 0.1) {
			if (!crafting) {
				if (world.getBlockState(pos.down(1)).isOf(Blocks.COPPER_BLOCK)) {
					if (world.getBlockState(pos.down(1).north(1)).isOf(Blocks.NETHERITE_BLOCK) || world.getBlockState(pos.down(1).south(1)).isOf(Blocks.NETHERITE_BLOCK)) {
						if (world.getBlockState(pos.down(1).east(1)).isOf(Blocks.NETHERITE_BLOCK) || world.getBlockState(pos.down(1).west(1)).isOf(Blocks.NETHERITE_BLOCK)) {
							if (world.getBlockState(pos.down(1).south(1)).isOf(Blocks.RAW_GOLD_BLOCK) || world.getBlockState(pos.down(1).north(1)).isOf(Blocks.RAW_GOLD_BLOCK)) {
								if (world.getBlockState(pos.down(1).west(1)).isOf(Blocks.RAW_GOLD_BLOCK) || world.getBlockState(pos.down(1).east(1)).isOf(Blocks.RAW_GOLD_BLOCK)) {
									if (AGE >= 600) {
										System.out.println("functgion");
										AGE = 0;
										crafting = true;
									} else {
										AGE++;
										System.out.println(AGE);
									}
								}
							}
						}
					}
				}
			}
			if (crafting) {

			}
		}
	}
}
