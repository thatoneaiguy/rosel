package com.thatoneaiguy.rosel.block.entity;

import com.thatoneaiguy.rosel.init.RoselBlockEntities;
import com.thatoneaiguy.rosel.init.RoselBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class RoughRoselBlockEntity extends BlockEntity {
	static int AGE = 0;
	static boolean crafting = false;

	public RoughRoselBlockEntity(BlockPos pos, BlockState state) {
		super(RoselBlockEntities.ROSEL_BLOCK, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, RoughRoselBlockEntity entity) {
		Random random = new Random();

		if (pos.getY() <= 0 && random.nextInt(8) == 0) {
			if (!world.getBlockState(pos.offset(Direction.DOWN)).isOf(Blocks.COPPER_BLOCK)) {
				return;
			} else if (!world.getBlockState(pos.offset(Direction.UP)).isOf(Blocks.NETHERITE_BLOCK)) {
				return;
			}


			Direction[] var6 = Direction.values();
			int var7 = var6.length;

			for(int var8 = 0; var8 < var7; ++var8) {
				Direction direction = var6[var8];
				if (direction.getAxis() != Direction.Axis.Y) {
					BlockPos offsetPos = pos.offset(direction);
					BlockState offsetState = world.getBlockState(offsetPos);
					if (!offsetState.isOf(Blocks.RAW_GOLD_BLOCK)) {
						return;
					}
				}
			}

			world.setBlockState(pos, RoselBlocks.PERFECT_ROSEL.getDefaultState());
			world.removeBlock(pos.offset(Direction.WEST), false);
			world.removeBlock(pos.offset(Direction.NORTH), false);
			world.removeBlock(pos.offset(Direction.EAST), false);
			world.removeBlock(pos.offset(Direction.SOUTH), false);
			world.removeBlock(pos.offset(Direction.UP), false);
			world.removeBlock(pos.offset(Direction.DOWN), false);


/*		if (random <= 0.1) {
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
			}*/
			if (crafting) {

			}
		}
	}
}
