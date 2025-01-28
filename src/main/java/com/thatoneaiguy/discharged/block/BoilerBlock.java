package com.thatoneaiguy.discharged.block;

import com.thatoneaiguy.discharged.util.boiler.BoilerPosition;
import com.thatoneaiguy.discharged.util.boiler.BoilerShape;
import com.thatoneaiguy.discharged.util.boiler.MultiblockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BoilerBlock extends Block {
	public static final EnumProperty<BoilerShape> SHAPE = EnumProperty.of("shape", BoilerShape.class);
	public static final EnumProperty<BoilerPosition> POSITION = EnumProperty.of("position", BoilerPosition.class);
	public static final DirectionProperty ROTATION = DirectionProperty.of("facing");
	public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

	public BoilerBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
			.with(SHAPE, BoilerShape.NONE)
			.with(POSITION, BoilerPosition.CENTER)
			.with(ROTATION, Direction.NORTH)
			.with(ACTIVE, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(SHAPE, POSITION, ROTATION, ACTIVE);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(ROTATION, ctx.getPlayerFacing().getOpposite());
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		MultiblockHelper.tryFormStructure(world, pos);

		super.onPlaced(world, pos, state, placer, itemStack);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient()) {
			boolean isActive = state.get(ACTIVE);
			world.setBlockState(pos, state.with(ACTIVE, !isActive));

			MultiblockHelper.tryFormStructure(world, pos);
		}
		return ActionResult.SUCCESS;
	}

	public void setShape(World world, BlockPos pos, BoilerShape shape, BoilerPosition position) {
		BlockState state = world.getBlockState(pos);
		world.setBlockState(pos, state.with(SHAPE, shape).with(POSITION, position).with(ACTIVE, true));
	}
}
