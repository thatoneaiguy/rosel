package com.thatoneaiguy.discharged.util.boiler;

import com.thatoneaiguy.discharged.DischargedConfig;
import com.thatoneaiguy.discharged.block.BoilerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.*;

public class MultiblockHelper {

	public static void tryFormStructure(World world, BlockPos origin) {
		List<BlockPos> connectedBlocks = findConnectedBlocks(world, origin);

		if (is1x3x1(connectedBlocks)) {
			updateStructure(world, connectedBlocks, BoilerShape.COLUMN);
		} else if (is2x1x2(connectedBlocks)) {
			updateStructure(world, connectedBlocks, BoilerShape.SQUARE);
		} else if (is3x1x3WithHeight(connectedBlocks)) {
			updateStructure(world, connectedBlocks, BoilerShape.TOWER);
		} else {
			resetStructure(world, connectedBlocks);
		}
	}

	Pair

	public static void tryDisbandStructure(World world, BlockPos origin) {
		List<BlockPos> connectedBlocks = findConnectedBlocks(world, origin);
		resetStructure(world, connectedBlocks);
	}

	private static List<BlockPos> findConnectedBlocks(World world, BlockPos origin) {
		List<BlockPos> result = new ArrayList<>();
		Queue<BlockPos> toVisit = new LinkedList<>();
		Set<BlockPos> visited = new HashSet<>();

		toVisit.add(origin);

		while (!toVisit.isEmpty()) {
			BlockPos current = toVisit.poll();

			if (visited.contains(current)) continue;

			visited.add(current);
			result.add(current);

			for (Direction direction : Direction.values()) {
				BlockPos neighbor = current.offset(direction);
				if (world.getBlockState(neighbor).isOf(world.getBlockState(origin).getBlock())) {
					toVisit.add(neighbor);
				}
			}
		}

		return result;
	}

	private static boolean is1x3x1(List<BlockPos> blocks) {
		return blocks.size() == 3 && areAligned(blocks, Direction.Axis.Y);
	}

	private static boolean is2x1x2(List<BlockPos> blocks) {
		return blocks.size() == 4 && isFlatSquare(blocks, 2);
	}

	private static boolean is3x1x3WithHeight(List<BlockPos> blocks) {
		int baseSize = 9; // 3x3 base
		int height = blocks.size() / baseSize;

		if (blocks.size() % baseSize != 0 || height > 10) {  // Assuming 10 is max height
			return false;
		}

		return hasConsistentBase(blocks, height);
	}

	private static boolean areAligned(List<BlockPos> blocks, Direction.Axis axis) {
		int constantCoordinate = blocks.get(0).get(axis);
		for (BlockPos pos : blocks) {
			if (pos.get(axis) != constantCoordinate) {
				return false;
			}
		}
		return true;
	}

	private static boolean isFlatSquare(List<BlockPos> blocks, int size) {
		Set<BlockPos> relativePositions = new HashSet<>();
		BlockPos origin = blocks.get(0);

		for (BlockPos pos : blocks) {
			relativePositions.add(pos.subtract(origin));
		}

		for (int x = 0; x < size; x++) {
			for (int z = 0; z < size; z++) {
				if (!relativePositions.contains(new BlockPos(x, 0, z))) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean hasConsistentBase(List<BlockPos> blocks, int height) {
		for (int y = 0; y < height; y++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					BlockPos pos = blocks.get(0).add(x, y, z);
					if (!blocks.contains(pos)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static void updateStructure(World world, List<BlockPos> blocks, BoilerShape shape) {
		for (BlockPos pos : blocks) {
			BlockState state = world.getBlockState(pos);
			BoilerPosition position = determinePosition(world, pos, blocks); // Determine if it's a center, edge, or corner
			world.setBlockState(pos, state.with(BoilerBlock.SHAPE, shape)
				.with(BoilerBlock.POSITION, position)
				.with(BoilerBlock.ACTIVE, true));
		}
	}

	private static void resetStructure(World world, List<BlockPos> blocks) {
		for (BlockPos pos : blocks) {
			BlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.with(BoilerBlock.SHAPE, BoilerShape.NONE)
				.with(BoilerBlock.POSITION, BoilerPosition.CENTER)
				.with(BoilerBlock.ACTIVE, false));
		}
	}

	private static BoilerPosition determinePosition(World world, BlockPos pos, List<BlockPos> blocks) {
		if (isCenter(pos, world, blocks)) {
			return BoilerPosition.CENTER;
		} else if (isEdge(pos, world, blocks)) {
			return BoilerPosition.EDGE;
		} else {
			return BoilerPosition.CORNER;
		}
	}

	private static boolean isCenter(BlockPos pos, World world, List<BlockPos> blocks) {
		Block block = world.getBlockState(pos).getBlock();
		if ( block instanceof BoilerBlock ) {
			if (pos.up().equals(block) && pos.down().equals(block)
				&& pos.north().equals(block) && pos.west().equals(block) && pos.east().equals(block) && pos.south().equals(block) ) {
				return true;
			}
		}
		return false;
	}

	private static boolean isEdge(BlockPos pos, World world, List<BlockPos> blocks) {
		Block block = world.getBlockState(pos).getBlock();
		if ( block instanceof BoilerBlock ) {
			if ( pos.north().equals(block) || pos.south().equals(block) || pos.east().equals(block) || pos.west().equals(block)) {
				if( !pos.up().equals(block) ) {
					return true;
				} else if( !pos.down().equals(block) ) {
					return true;
				}
			}
		}
		return false;
	}
}
