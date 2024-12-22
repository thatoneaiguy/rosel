package com.thatoneaiguy.discharged.item;

import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.DischargedClient;
import com.thatoneaiguy.discharged.DischargedConfig;
import com.thatoneaiguy.discharged.event.KeyInputHandler;
import com.thatoneaiguy.discharged.init.DischargedLodestoneParticles;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;
import xyz.amymialee.mialeemisc.util.MialeeMath;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class RoselGauntletItem extends BaseRoselWeapon implements IAnimatable, ISyncable {
	public GauntletMode currentMode;
	public double predicate;
	private static final HashMap<UUID, Integer> activeUsers = new HashMap<>();
	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public RoselGauntletItem(QuiltItemSettings settings) {
		super(ToolMaterials.NETHERITE, 3, -3.2F, settings);
		this.currentMode = GauntletMode.DEFLECT;
		this.predicate = 0.1;

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			Iterator<Map.Entry<UUID, Integer>> iterator = activeUsers.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<UUID, Integer> entry = iterator.next();
				int ticksLeft = entry.getValue() - 1;
				if (ticksLeft <= 0) {
					iterator.remove(); // Remove the player when the effect expires
					iterator.remove(); // Remove the player when the effect expires
				} else {
					entry.setValue(ticksLeft); // Decrement the tick count
				}
			}
		});

		GeckoLibNetwork.registerSyncable(this);
	}

	private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		AnimationController<RoselGauntletItem> controller = new AnimationController<>(this, "idle", 20, this::predicate);
		data.addAnimationController(controller);
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public void onAnimationSync(int id, int state) {
	}

	public void toggleMode(ItemStack stack) {
		NbtCompound compound = stack.getOrCreateNbt();
		int select = MialeeMath.clampLoop(compound.getInt("action"), 0, GauntletMode.values().length);
		setMode(stack, select);
	}

	public GauntletMode getMode(ItemStack stack) {
		return GauntletMode.values()[stack.getOrCreateNbt().getInt("Mode")];
	}


	public static Identifier getModeTexture(ItemStack stack) {
		GauntletMode mode = GauntletMode.values()[stack.getOrCreateNbt().getInt("Mode")];
		Identifier texture = new Identifier(Discharged.MODID, "textures/item/rosel_gauntlet_blue");

		switch (mode) {
			case DEFLECT -> new Identifier(Discharged.MODID, "textures/item/rosel_gauntlet_blue");
			case SHOCKWAVE -> new Identifier(Discharged.MODID, "textures/item/rosel_gauntlet_red");
			case CONDUCTOR ->  new Identifier(Discharged.MODID, "textures/item/rosel_gauntlet_yellow");
		}
		return texture;
	}

	public void setMode(ItemStack stack, int newMode) {
		stack.getOrCreateNbt().putInt("Mode", newMode);
	}

	public String modeConverter() {
		if ( currentMode == GauntletMode.DEFLECT ) {
			return "§3Deflect";
		} else if ( currentMode == GauntletMode.SHOCKWAVE ) {
			return "§cShockwave";
		} else if ( currentMode == GauntletMode.CONDUCTOR) {
			return "§6Conductor";
		}
		return ("[An error occurred. Please press [CHANGE ARM] to change to another mode.]".formatted(Formatting.DARK_RED));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if ( currentMode == GauntletMode.DEFLECT ) {
			handleDeflect(world, user);
		}
		else if ( currentMode == GauntletMode.SHOCKWAVE ) {
			handleShockwave(world, user);
		}
		else if ( currentMode == GauntletMode.CONDUCTOR) {
			handleShocker(world, user);
		}

		return super.use(world, user, hand);
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		World world = user.getWorld();

		if ( currentMode == GauntletMode.DEFLECT ) {
			handleDeflect(world, user);
		}
		else if ( currentMode == GauntletMode.SHOCKWAVE ) {
			handleShockwave(world, user);
		}
		else if ( currentMode == GauntletMode.CONDUCTOR) {
			handleShocker(world, user);
		}

		return super.useOnEntity(stack, user, entity, hand);
	}

	private void handleDeflect(World  world, PlayerEntity user) {
		reflectProjectile(world, user);
		activateDamageReflection(user);
	}

	private void handleShockwave(World world, PlayerEntity user) {
		world.addParticle(DischargedClient.SHOCKWAVE, user.getX() + 0.5, user.getY() + 0.5, user.getZ() + 0.5, 0.0, 0.0, 0.0);
		applyVelocityImpact(world, user);
	}

	private void handleShocker(World world, PlayerEntity user) {
		if (!world.isClient) {
			Vec3d userPos = user.getEyePos();
			Vec3d lookVec = user.getRotationVec(1.0F).normalize();
			double range = 15.0;
			float damage = 5.0f;
			int particleCount = 20;

			Vec3d targetPos = userPos.add(lookVec.multiply(range));
			Box searchBox = new Box(userPos, targetPos);
			List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, searchBox, e -> e != user);

			LivingEntity nearestEntity = null;
			double nearestDistance = Double.MAX_VALUE;

			for (Entity foundEntity : entities) {
				double distance = foundEntity.getPos().distanceTo(userPos);
				if (distance < nearestDistance) {
					nearestDistance = distance;
					nearestEntity = (LivingEntity) foundEntity;
				}
			}

			if (nearestEntity != null) {
				nearestEntity.damage(DamageSource.player(user), damage);
				spawnParticleOnLine(world, userPos, nearestEntity.getEyePos(), particleCount);
			}
		}
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if ( DischargedConfig.gauntletKBType == DischargedConfig.GauntletKBType.DIRECTIONAL ) {
			if (attacker instanceof PlayerEntity player) {
				Vec3d lookVector = player.getRotationVec(1.0F).normalize();
				double knockbackStrength = 1.5;
				Vec3d knockback = lookVector.multiply(knockbackStrength);

				target.addVelocity(knockback.x, knockback.y, knockback.z);
				target.velocityModified = true;
			}
		}

		return super.postHit(stack, target, attacker);
	}

	private void applyVelocityImpact(World world, PlayerEntity user) {
		Vec3d userVelocity = user.getVelocity();
		double velocityMagnitude = userVelocity.length();

		double aoeRadius = 15.0;
		List<Entity> targets = world.getOtherEntities(user, user.getBoundingBox().expand(aoeRadius));
		for (Entity target : targets) {
			if (target instanceof LivingEntity livingTarget) {
				float damage = (float) Math.min(velocityMagnitude * 4, 20);
				livingTarget.damage(DamageSource.player(user), damage);

				if (!livingTarget.isOnGround()) {
					Vec3d knockback = target.getPos().subtract(user.getPos()).normalize().multiply(2.0).add(0, 1.5, 0);
					livingTarget.setVelocity(knockback);
				}
			}
		}

		if (!user.isOnGround()) {
			Vec3d selfKnockback = userVelocity.normalize().multiply(-2).add(0, 1.5, 0);
			user.setVelocity(selfKnockback);
		}

		user.velocityModified = true;
	}

	private void reflectProjectile(World world, PlayerEntity user) {
		Vec3d userEyePos = user.getEyePos();
		Vec3d lookVec = user.getRotationVec(1.0F).normalize();
		double detectionDistance = 2.0;

		Vec3d targetCenter = userEyePos.add(lookVec.multiply(detectionDistance));
		Box detectionBox = new Box(
			targetCenter.x - 1, targetCenter.y - 1, targetCenter.z - 1,
			targetCenter.x + 1, targetCenter.y + 1, targetCenter.z + 1
		);

		List<ProjectileEntity> entities = world.getEntitiesByClass(
			ProjectileEntity.class,
			detectionBox,
			entity -> !entity.isRemoved()
		);

		if (entities.isEmpty()) {
			System.out.println("No projectiles found within the detection area.");
			return;
		}

		for (Entity entity : entities) {
			System.out.println("Projectile detected: " + entity);

			if (entity instanceof ProjectileEntity projectile) {
				Entity owner = projectile.getOwner();
				if (owner != null) {
					Vec3d reflectionDirection = owner.getPos().subtract(projectile.getPos()).normalize();
					projectile.setVelocity(reflectionDirection.multiply(1.5)); // Adjust speed as needed
					System.out.println("Projectile reflected to owner.");
				} else {
					Vec3d backwardDirection = projectile.getPos().subtract(user.getPos()).normalize();
					projectile.setVelocity(backwardDirection.multiply(1.5));
					System.out.println("Projectile reflected backward.");
				}

				break;
			}
		}
	}

	private void activateDamageReflection(PlayerEntity user) {
		UUID userId = user.getUuid();
		activeUsers.put(userId, DischargedConfig.ticks);
	}

	public static boolean handleDamageReflection(LivingEntity entity, DamageSource source, float amount) {
		if (entity instanceof PlayerEntity player && activeUsers.containsKey(player.getUuid())) {
			Entity attacker = source.getSource();
			if (attacker instanceof LivingEntity livingAttacker) {
				livingAttacker.damage(DamageSource.thorns(player), amount);

				player.heal(amount + DischargedConfig.parry_healing);
				activeUsers.remove(player.getUuid());

				return false;
			}
		}
		return true;
	}

	public List<LivingEntity> findClosestEntities(PlayerEntity player, World world, int maxResults) {
		Vec3d playerPos = player.getPos();

		return world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(50), entity -> entity != player).stream()
			.sorted(Comparator.comparingDouble(entity -> entity.squaredDistanceTo(playerPos)))
			.limit(maxResults)
			.collect(Collectors.toList());
	}

	public void spawnParticleOnLine(World world, Vec3d start, Vec3d end, int particleCount) {
		Vec3d direction = end.subtract(start).normalize();
		double distance = start.distanceTo(end);
		double spacing = distance / (particleCount - 1);

		for (int i = 0; i < particleCount; i++) {
			Vec3d particlePos = start.add(direction.multiply(i * spacing));

			if (DischargedConfig.particleTypes == DischargedConfig.ParticleTypes.LODESTONE) {
				ParticleBuilders.create(DischargedLodestoneParticles.ROSEL_BOLT)
					.overrideAnimator(SimpleParticleEffect.Animator.WITH_AGE)
					.setScale(0.2f + world.random.nextFloat() / 3f)
					.setSpinOffset(world.random.nextFloat() * 360f)
					.setSpin((float) (world.random.nextGaussian() / 100f))
					.setAlpha(0, 0.3f, 0)
					.setAlphaEasing(Easing.QUINTIC_OUT)
					.enableNoClip()
					.setLifetime(5)
					.spawn(world, particlePos.getX(), particlePos.getY(), particlePos.getZ());
			} else {
				world.addParticle(DischargedClient.ROSEL_CROSS_V, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0.0, 0.0, 0.0);
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if ( !Screen.hasShiftDown() ) {
			tooltip.add(Text.literal("Press %s to change modes.".formatted(KeyInputHandler.swapArmKey.getTranslationKey())).formatted(Formatting.DARK_GRAY));
			tooltip.add(Text.literal("Current mode is %s".formatted(modeConverter())).formatted(Formatting.DARK_GRAY));
			tooltip.add(Text.literal(" "));
			tooltip.add(Text.literal("Press §a[SHIFT]§r to show more info").formatted(Formatting.DARK_GRAY));
		}

		else {
			switch (currentMode) {
				case DEFLECT:
					tooltip.add(Text.literal("Holding Right-Click allows you to block Projectiles").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					tooltip.add(Text.literal("You can §3Parry§r incoming attacks by Punching").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					break;
				case SHOCKWAVE:
					tooltip.add(Text.literal("Right-Clicking allows you to create §cExplosions").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					tooltip.add(Text.literal("More Knockback on Basic Attacks").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					break;
				case CONDUCTOR:
					tooltip.add(Text.literal("Charge §6Conduction§r by holding Right-Click").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					tooltip.add(Text.literal("Using Charge shoots Bolts of §6Rosel Energy").formatted(Formatting.DARK_GRAY));
					tooltip.add(Text.literal(" "));
					break;
			}
		}

		super.appendTooltip(stack, world, tooltip, context);
	}

	static {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			if (entity instanceof PlayerEntity player && activeUsers.containsKey(player.getUuid())) {
				// Reflect damage back to the attacker
				Entity attacker = source.getSource();
				if (attacker instanceof LivingEntity livingAttacker) {
					livingAttacker.damage(DamageSource.thorns(player), amount);
				}

				player.heal(amount + 2.0F);

				return false;
			}
			return true;
		});
	}



	public enum GauntletMode {
		DEFLECT,
		SHOCKWAVE,
		CONDUCTOR
	}
}


