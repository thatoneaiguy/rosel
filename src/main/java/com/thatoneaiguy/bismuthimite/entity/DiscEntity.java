package com.thatoneaiguy.bismuthimite.entity;

import com.thatoneaiguy.bismuthimite.init.BismuthimiteEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DiscEntity extends PersistentProjectileEntity {
	boolean first = true;
	boolean checking = false;
	Vec3d savedVel;

	int ticks = 20;

	boolean brimstone = false;
	boolean accumulate = false;

	public DiscEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	public DiscEntity(World world, PlayerEntity user, double v, double v1, double v2) {
		super(BismuthimiteEntities.DiscEntityType, world);
		this.addVelocity(v * 2, v1 * 2, v2 * 2);
	}

	@Override
	public void tick() {
		if (first) {
			first = false;
			new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				savedVel = this.getVelocity();
				checking = true;
				comboChecker();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
				checking = false;

            }).start();
		}
		super.tick();
	}

	private void comboChecker() {
		if (checking) {
			this.setVelocity(0, 0, 0);

			if (!accumulate) {
				this.setVelocity(savedVel);
			}
		}
	}

	@Override
	protected ItemStack asItemStack() {
		return null;
    }

	@Override
	protected void onCollision(HitResult hitResult) {

		super.onCollision(hitResult);
	}
}
