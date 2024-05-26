package com.thatoneaiguy.rosel.entity;

import com.thatoneaiguy.rosel.init.RoselEntities;
import com.thatoneaiguy.rosel.init.RoselItems;
import com.thatoneaiguy.rosel.util.RaycastUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DiscEntity extends PersistentProjectileEntity {
	private static final TrackedData<Boolean> ENCHANTED = null;
	private ItemStack discStack;

	boolean first = true;
	boolean checking = false;
	boolean returning = false;
	Vec3d savedVel;

	public int returnTimer;

	boolean brimstone = false;
	boolean accumulate = false;

	public DiscEntity(EntityType<? extends DiscEntity> entityType, World world) {
		super(entityType, world);
//		this.discStack = new ItemStack(RoselItems.ROSEL_DISC);
	}

	public DiscEntity(World world, PlayerEntity user, ItemStack stack) {
		super(RoselEntities.DiscEntityType, world);
//		this.addVelocity(v * 2, v1 * 2, v2 * 2);
		this.setDamage(1);
		this.setOwner(user);
		this.discStack = new ItemStack(Items.TRIDENT);
		this.discStack = stack.copy();
		this.pickupType = PickupPermission.DISALLOWED;
		this.dataTracker.set(ENCHANTED, stack.hasGlint());
	}

	public DiscEntity(World world, PlayerEntity user, double v, double v1, double v2) {
		super(RoselEntities.DiscEntityType, world);

		this.addVelocity(v * 2, v1 * 2, v2 * 2);
		this.setDamage(1);
		this.setOwner(user);
		this.pickupType = PickupPermission.ALLOWED;
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ENCHANTED, false);
	}

	public boolean isEnchanted() {
		return (Boolean)this.dataTracker.get(ENCHANTED);
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
				comboChecker((PlayerEntity) this.getOwner());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
				checking = false;

				if (!accumulate) {
					this.setVelocity(savedVel);
				}
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

				returning = true;

            }).start();
		}

		if (checking) {
			comboChecker((PlayerEntity) this.getOwner());
		}

		if (returning) {
			Entity entity = this.getOwner();
			if (!this.isOwnerAlive()) {
				if (!this.world.isClient && this.pickupType == PickupPermission.ALLOWED) {
					this.dropStack(this.asItemStack(), 0.1F);
				}
			} else {

				this.setNoClip(true);
				Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
				this.setPos(this.getX(), this.getY() + vec3d.y * 0.015, this.getZ());
				if (this.world.isClient) {
					this.lastRenderY = this.getY();
				}

				double d = 0.05;
				this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
				if (this.returnTimer == 0) {
					this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
				}
				++this.returnTimer;
			}
		}

		super.tick();
	}

	private void comboChecker(PlayerEntity user) {
		if (checking) {

			HitResult hitResult = RaycastUtil.performRaycast(user, 20);

			if ( hitResult.getType() == HitResult.Type.ENTITY && user.isHolding(RoselItems.ROSEL_KAPIS)) {
				EntityHitResult entity = (EntityHitResult) hitResult;

				System.out.println("Entity hit:" + entity.getEntity());
			}

			this.setVelocity(0, 0, 0);
		}
	}

	@Override
	protected ItemStack asItemStack() {
		return Items.AIR.getDefaultStack();
    }

	@Override
	protected void onCollision(HitResult hitResult) {

//		if (hitResult.getType().name().equals("firework_rocket")) {
//			System.out.println("lets fucvking go");
//        }
//
//		super.onCollision(hitResult);
	}

	private boolean isOwnerAlive() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
		} else {
			return false;
		}
	}
}
