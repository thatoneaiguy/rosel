package com.thatoneaiguy.discharged.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class RoselCrystalPlayerShapeEntity extends HostileEntity {
	public static final TrackedData<Boolean> alive = null;

	public RoselCrystalPlayerShapeEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.dataTracker.set(alive, false);
	}

	@Override
	protected void initGoals() {


		super.initGoals();
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) { return super.writeNbt(nbt); }

	@Override
	public void readNbt(NbtCompound nbt) { super.readNbt(nbt); }
}
