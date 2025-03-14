package com.thatoneaiguy.discharged.mixin;

import com.thatoneaiguy.discharged.DischargedClient;
import com.thatoneaiguy.discharged.init.DischargedBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin {

	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	private void injectCustomDrip(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		BlockPos abovePos = pos.up();
		BlockState aboveState = world.getBlockState(abovePos);
		if (aboveState.isOf(DischargedBlocks.ROUGH_ROSEL)) {
			if (random.nextFloat() < 0.02F) {
				world.setBlockState(abovePos, DischargedBlocks.PERFECT_ROSEL.getDefaultState());
			}
			ci.cancel();
		}
	}

	// Make the invoker method abstract
	@Invoker("createParticle")
	public abstract void invokeCreateParticle(World world, BlockPos pos, BlockState state, Fluid fluid);

	// Override the createParticle method to use custom particles for RoselFluid
	@Unique
	public void createDripstoneParticle(World world, BlockPos pos, BlockState state, Fluid fluid) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		double d = 0.0625;
		double e = (double)pos.getX() + 0.5 + vec3d.x;
		double f = (double)((float)(pos.getY() + 1) - 0.6875F) - 0.0625;
		double g = (double)pos.getZ() + 0.5 + vec3d.z;
		ParticleEffect particleEffect;

		if (fluid.isIn(FluidTags.LAVA)) {
			particleEffect = ParticleTypes.DRIPPING_DRIPSTONE_LAVA;
		} else if (fluid.isIn(FluidTags.WATER)) {
			particleEffect = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
		} else {
			particleEffect = DischargedClient.DRIPPING_ROSEL_DROP;
		}

		world.addParticle(particleEffect, e, f, g, 0.0, 0.0, 0.0);
	}
}
