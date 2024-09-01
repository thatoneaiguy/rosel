package com.thatoneaiguy.rosel.fluid;

import com.thatoneaiguy.rosel.init.RoselFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class PureRosel extends FlowableFluid {

	@Override
	protected boolean isInfinite() {
		return false;
	}

	@Override
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}

	@Override
	protected int getFlowSpeed(WorldView world) {
		return 0;
	}

	@Override
	public int getLevel(FluidState state) {
		return 8;
	}

	@Override
	protected int getLevelDecreasePerBlock(WorldView world) {
		return 1;
	}

	@Override
	public int getTickRate(WorldView world) {
		return 5;
	}

	@Override
	protected float getBlastResistance() {
		return 100F;
	}

	@Override
	protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	public Fluid getStill() {
		return RoselFluids.STILL_PURE_ROSEL;
	}

	@Override
	public Fluid getFlowing() {
		return RoselFluids.FLOWING_PURE_ROSEL;
	}

	@Override
	protected BlockState toBlockState(FluidState state) {
		return RoselFluids.PURE_ROSEL_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
	}

	@Override
	public Item getBucketItem() {
		return null;
	}

	@Override
	public boolean isSource(FluidState state) {
		return false;
	}

	public static class Flowing extends PureRosel {
		public Flowing() {}

		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);

			builder.add(LEVEL);
		}

		@Override
		public int getLevel(FluidState state) {
			return state.getLevel();
		}
	}

	public static class Still extends PureRosel {
		public Still() {}

		@Override
		public int getLevel(FluidState state) {
			return 0;
		}
	}
}
