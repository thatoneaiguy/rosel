package com.thatoneaiguy.discharged.particle;

import com.thatoneaiguy.discharged.DischargedClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class RoselDrip extends SpriteBillboardParticle {
	RoselDrip(ClientWorld world, double x, double y, double z, Fluid fluid) {
		super(world, x, y, z);
		this.setBoundingBoxSpacing(0.01f, 0.01f);
		this.gravityStrength = 0.06f;
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public int getBrightness(float tint) {
		return super.getBrightness(tint);
	}

	@Override
	public void tick() {
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;
		this.updateAge();
		if (this.dead) {
			return;
		}
		this.velocityY -= (double) this.gravityStrength;
		this.move(this.velocityX, this.velocityY, this.velocityZ);
		this.updateVelocity();
		if (this.dead) {
			return;
		}
		this.velocityX *= (double) 0.98f;
		this.velocityY *= (double) 0.98f;
		this.velocityZ *= (double) 0.98f;
		BlockPos blockPos = new BlockPos(this.x, this.y, this.z);
		FluidState fluidState = this.world.getFluidState(blockPos);
		if (this.y < (double) ((float) blockPos.getY() + fluidState.getHeight(this.world, blockPos))) {
			this.markDead();
		}
	}

	protected void updateAge() {
		if (this.maxAge-- <= 0) {
			this.markDead();
		}
	}

	protected void updateVelocity() {
	}

	@ClientOnly
	public static class LandingFollyRedPaintDropFactory
		implements ParticleFactory<DefaultParticleType> {
		protected final SpriteProvider spriteProvider;

		public LandingFollyRedPaintDropFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			Landing blockLeakParticle = new Landing(clientWorld, d, e, f, Fluids.EMPTY);
			blockLeakParticle.setMaxAge((int) (28.0 / (Math.random() * 0.8 + 0.2)));
			blockLeakParticle.setColor(0.39f, 0.38f, 0.25f);
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	@ClientOnly
	public static class FallingFollyRedPaintDropFactory implements ParticleFactory<DefaultParticleType> {
		protected final SpriteProvider spriteProvider;

		public FallingFollyRedPaintDropFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			ContinuousFalling blockLeakParticle = new ContinuousFalling(clientWorld, d, e, f, Fluids.EMPTY, DischargedClient.LANDING_ROSEL_DROP);
			blockLeakParticle.gravityStrength = 0.01f;
			blockLeakParticle.setColor(0.39f, 0.38f, 0.25f);
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	@ClientOnly
	public static class DrippingFollyRedPaintDropFactory implements ParticleFactory<DefaultParticleType> {
		protected final SpriteProvider spriteProvider;

		public DrippingFollyRedPaintDropFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			Dripping dripping = new Dripping(clientWorld, d, e, f, Fluids.EMPTY, DischargedClient.FALLING_ROSEL_DROP);
			dripping.gravityStrength *= 0.01f;
			dripping.setMaxAge(100);
			dripping.setColor(0.39f, 0.38f, 0.25f);
			dripping.setSprite(this.spriteProvider);
			return dripping;
		}
	}

	@ClientOnly
	static class Dripping extends RoselDrip {
		private final ParticleEffect nextParticle;

		Dripping(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle) {
			super(world, x, y, z, fluid);
			this.nextParticle = nextParticle;
			this.gravityStrength *= 0.02f;
			this.maxAge = 40;
		}

		@Override
		protected void updateAge() {
			if (this.maxAge-- <= 0) {
				this.markDead();
				this.world.addParticle(this.nextParticle, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
			}
		}

		@Override
		protected void updateVelocity() {
			this.velocityX *= 0.02;
			this.velocityY *= 0.02;
			this.velocityZ *= 0.02;
		}
	}

	@Environment(value = EnvType.CLIENT)
	static class ContinuousFalling extends Falling {
		protected final ParticleEffect nextParticle;

		ContinuousFalling(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle) {
			super(world, x, y, z, fluid);
			this.nextParticle = nextParticle;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.markDead();
				this.world.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
			}
		}
	}

	@ClientOnly
	static class Falling extends RoselDrip {
		Falling(ClientWorld clientWorld, double d, double e, double f, Fluid fluid) {
			this(clientWorld, d, e, f, fluid, (int) (64.0 / (Math.random() * 0.8 + 0.2)));
		}

		Falling(ClientWorld world, double x, double y, double z, Fluid fluid, int maxAge) {
			super(world, x, y, z, fluid);
			this.maxAge = maxAge;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.markDead();
			}
		}
	}

	@ClientOnly
	static class Landing extends RoselDrip {
		Landing(ClientWorld clientWorld, double d, double e, double f, Fluid fluid) {
			super(clientWorld, d, e, f, fluid);
			this.maxAge = (int) (16.0 / (Math.random() * 0.8 + 0.2));
		}
	}

}
