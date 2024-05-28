	package com.thatoneaiguy.rosel.entity;

	import com.thatoneaiguy.rosel.init.RoselEntities;
	import com.thatoneaiguy.rosel.init.RoselItems;
	import com.thatoneaiguy.rosel.item.DiscItem;
	import net.minecraft.entity.Entity;
	import net.minecraft.entity.EntityType;
	import net.minecraft.entity.data.DataTracker;
	import net.minecraft.entity.data.TrackedData;
	import net.minecraft.entity.data.TrackedDataHandlerRegistry;
	import net.minecraft.entity.player.PlayerEntity;
	import net.minecraft.entity.projectile.PersistentProjectileEntity;
	import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
	import net.minecraft.item.Item;
	import net.minecraft.item.ItemStack;
	import net.minecraft.item.Items;
	import net.minecraft.server.network.ServerPlayerEntity;
	import net.minecraft.sound.SoundEvents;
	import net.minecraft.util.hit.EntityHitResult;
	import net.minecraft.util.hit.HitResult;
	import net.minecraft.util.math.Vec3d;
	import net.minecraft.world.World;
	import org.jetbrains.annotations.Nullable;

	public class DiscEntity extends ThrownItemEntity {
	private static final TrackedData<Boolean> ENCHANTED = null;
	private static final TrackedData<Integer> PLAYER_OWNER;
	private ItemStack discStack;
	private PlayerEntity playerOwner;

	boolean first = true;
	int ticks = 0;

	boolean checking = false;
	boolean returning = false;
	Vec3d savedVel;

	public int returnTimer;

	boolean brimstone = false;
	boolean accumulate = false;

	public DiscEntity(EntityType<? extends DiscEntity> entityType, World world) {
		super(entityType, world);
		this.discStack = new ItemStack(RoselItems.ROSEL_DISC);
	}

	public DiscEntity(World world, PlayerEntity user) {
		super(RoselEntities.DiscEntityType, world);
		//this.addVelocity(v * 2, v1 * 2, v2 * 2);
		//this.setDamage(1);
		this.setOwner(user);
		this.discStack = new ItemStack(Items.TRIDENT);
		this.discStack = discStack.copy();
		this.dataTracker.set(ENCHANTED, discStack.hasGlint());
	}

	public DiscEntity(World world, PlayerEntity user, double v, double v1, double v2) {
		super(RoselEntities.DiscEntityType, world);

		this.addVelocity(v * 2, v1 * 2, v2 * 2);
		//this.setDamage(1);
	}

	@Override
	protected Item getDefaultItem() {
		return RoselItems.ROSEL_DISC;
	}

	protected void initDataTracker() {
		this.dataTracker.startTracking(PLAYER_OWNER, 0);
		super.initDataTracker();
		//this.dataTracker.startTracking(ENCHANTED, false);
	}

	public boolean isEnchanted() {
		return (Boolean)this.dataTracker.get(ENCHANTED);
	}

	@Override
	public void tick() {
		PlayerEntity owner = this.getPlayerOwner();

		ticks++;

		if ( first ) {
			if ( ticks == 2) {
				savedVel = this.getVelocity();
				checking = true;
				comboChecker((PlayerEntity) this.getOwner());
				ticks = 0;
			}

			if ( ticks == 20 ) {
				checking = false;

				if (!accumulate) {
					this.setVelocity(savedVel);
				}
				ticks = 0;
			}

			if ( ticks == 200) {
				returning = true;
			}

			if (checking) {
				comboChecker((PlayerEntity) this.getOwner());
			}

			/*if ( returning ) {
				this.getOwner();
				Vec3d pos = this.getPos();
				Vec3d towards;
				double d;
				if ( returning ) {
					towards = owner.getEyePos();
					d = pos.distanceTo(towards);
					if (d < 2.0) {
						this.removeDisc();
						return;
					}

					double speedToReturn = Math.max(d / 48.0, 0.25);
					Vec3d vec3d = owner.getEyePos().subtract(this.getPos()).normalize().multiply(speedToReturn);
					this.setVelocity(this.getVelocity().multiply(0.75).add(vec3d));
				}
			}*/

		}

		/*if (first) {
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
				if (!this.world.isClient *//*&& this.pickupType == PickupPermission.ALLOWED*//*) {
					this.dropStack(discStack);
				}
			} else {

				//this.setNoClip(true);
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
		}*/

		super.tick();
	}
	// found me!
	// winter wtf

	private void comboChecker(PlayerEntity user) {
		if (checking) {

			this.setVelocity(0, 0, 0);
		}
	}

	//	@Override
	//	protected ItemStack asItemStack() {
	//		return Items.AIR.getDefaultStack();
	//    }

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

	public @Nullable PlayerEntity getPlayerOwner() {
		if (this.playerOwner != null) {
			return this.playerOwner;
		} else {
			int id = (Integer)this.dataTracker.get(PLAYER_OWNER);
			if (id != -1) {
				Entity entity = this.world.getEntityById(id);
				if (entity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity)entity;
					this.playerOwner = player;
					return player;
				}
			}

			this.discard();
			return null;
		}
	}

	private void removeDisc() {
		if (!this.world.isClient()) {
			this.discard();
		}
	}

	static {
		PLAYER_OWNER = DataTracker.registerData(DiscEntity.class, TrackedDataHandlerRegistry.INTEGER);
	}
	}
