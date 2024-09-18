package com.thatoneaiguy.rosel.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class DiscEntity extends ThrownItemEntity {
	public DiscEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected Item getDefaultItem() {
		return null;
	}
}
