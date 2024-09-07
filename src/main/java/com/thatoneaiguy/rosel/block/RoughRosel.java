package com.thatoneaiguy.rosel.block;

import com.thatoneaiguy.rosel.RoselClient;
import com.thatoneaiguy.rosel.init.RoselBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class RoughRosel extends Block {
	public RoughRosel(Settings settings) {
		super(settings);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.random.RandomGenerator random) {
		if (random.nextInt(5) != 0) {
			return;
		}
		Direction direction = Direction.random(random);
		if (direction == Direction.UP) {
			return;
		}
		BlockPos blockPos = pos.offset(direction);
		BlockState blockState = world.getBlockState(blockPos);
		if (state.isOpaque() && blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
			return;
		}
		double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double) direction.getOffsetX() * 0.6;
		double e = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double) direction.getOffsetY() * 0.6;
		double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double) direction.getOffsetZ() * 0.6;
		world.addParticle(RoselClient.DRIPPING_ROSEL_DROP, (double) pos.getX() + d, (double) pos.getY() + e, (double) pos.getZ() + f, 0.0, 0.0, 0.0);
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return state.getBlock() == RoselBlocks.ROUGH_ROSEL;
	}
}
