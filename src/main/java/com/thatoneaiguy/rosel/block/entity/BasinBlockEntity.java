package com.thatoneaiguy.rosel.block.entity;

import com.thatoneaiguy.rosel.block.Basin;
import com.thatoneaiguy.rosel.init.RoselBlockEntities;
import com.thatoneaiguy.rosel.init.RoselBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasinBlockEntity extends BlockEntity {
	static int AGE = 0;

	public BasinBlockEntity(BlockPos pos, BlockState state) {
		super(RoselBlockEntities.BASIN, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, BasinBlockEntity entity) {

		double random = Math.random();
		int one = 1;
		int two = 2;

		if ( random <= 0.1) {
			if (world.getBlockState(pos.down(one)).isOf(Blocks.SOUL_CAMPFIRE)) {
				if (world.getBlockState(pos.up(one)).isOf(Blocks.POINTED_DRIPSTONE)) {
					if (world.getBlockState(pos.up(two)).isOf(RoselBlocks.ROUGH_ROSEL)) {
						if (AGE >= 600) {
							System.out.println("functgion");
							if ( !world.isClient ) {
								world.setBlockState(pos, state.cycle(Basin.FILLED));
							}
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
