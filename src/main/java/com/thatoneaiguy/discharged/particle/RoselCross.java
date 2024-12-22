package com.thatoneaiguy.discharged.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;

public class RoselCross extends SpriteBillboardParticle {

	public RoselCross(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
		super(clientWorld, d, e, f);
		this.setBoundingBoxSpacing(0.02F, 0.02F);
		this.scale *= this.random.nextFloat() * 0.6F + 0.2F;
		this.velocityX = g * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
		this.velocityY = h * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
		this.velocityZ = i * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
		this.maxAge = 1;
	}

	@Override
	public void tick() {
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;
		if (this.age++ >= this.maxAge) {
			this.markDead();
		} else {
			this.velocityY += 0.002;
			this.move(this.velocityX, this.velocityY, this.velocityZ);
			this.velocityX *= 0.8500000238418579;
			this.velocityY *= 0.8500000238418579;
			this.velocityZ *= 0.8500000238418579;
			if (!this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
				this.markDead();
			}
		}
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}
}
