package com.thatoneaiguy.rosel.block;

import com.thatoneaiguy.rosel.block.entity.BasinBlockEntity;
import com.thatoneaiguy.rosel.init.RoselBlockEntities;
import com.thatoneaiguy.rosel.init.RoselItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Basin extends BlockWithEntity implements BlockEntityProvider{

	public static final BooleanProperty FILLED = BooleanProperty.of("filled");

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if ( world.getBlockState(pos).equals("filled") ) {
			player.swingHand(Hand.MAIN_HAND);
			player.giveItemStack(RoselItems.ROSEL.getDefaultStack());
		}

		return super.onUse(state, world, pos, player, hand, hit);
	}

	/* BLOCK ENTITY */

	public Basin(Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new BasinBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, RoselBlockEntities.BASIN, BasinBlockEntity::tick);
	}
}
