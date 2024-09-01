package com.thatoneaiguy.rosel.block;

import com.thatoneaiguy.rosel.block.entity.RoughRoselBlockEntity;
import com.thatoneaiguy.rosel.init.RoselBlockEntities;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RoughRosel extends BlockWithEntity implements BlockEntityProvider {
	public RoughRosel(Settings settings) {
		super(settings);
	}

	@Nullable @Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RoughRoselBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable @Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, RoselBlockEntities.ROSEL_BLOCK, RoughRoselBlockEntity::tick);
	}
}
