 package com.thatoneaiguy.rosel.item;

 import com.sammy.lodestone.systems.rendering.particle.Easing;
 import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
 import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
 import com.thatoneaiguy.rosel.Rosel;
 import com.thatoneaiguy.rosel.RoselClient;
 import com.thatoneaiguy.rosel.RoselConfig;
 import com.thatoneaiguy.rosel.event.KeyInputHandler;
 import com.thatoneaiguy.rosel.init.RoselDamageSources;
 import com.thatoneaiguy.rosel.init.RoselLodestoneParticles;
 import net.minecraft.client.gui.screen.Screen;
 import net.minecraft.client.item.TooltipContext;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ToolMaterials;
 import net.minecraft.text.Text;
 import net.minecraft.util.Formatting;
 import net.minecraft.util.Hand;
 import net.minecraft.util.TypedActionResult;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.world.World;
 import org.jetbrains.annotations.Nullable;
 import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

 import java.util.Comparator;
 import java.util.List;
 import java.util.stream.Collectors;

 public class RoselGauntletItem extends BaseRoselWeapon {
	public static double mode = 0.1;

	/*
	* 1 = DEFLECT
	* 2 = SHOCKWAVE
	* 3 = CONDUCTOR
	*/


	public RoselGauntletItem(QuiltItemSettings settings) {
		super(ToolMaterials.NETHERITE, 3, -2.4F, settings);
	}

	public static double getMode(ItemStack stack) {
		return mode;
	}

	public static String modeConverter() {
		if ( mode == 0.1) {
			return "§3Deflect";
		} else if ( mode == 0.2) {
			return "§cShockwave";
		} else if ( mode == 0.3) {
			return "§6Conductor";
		}
		return ("[An error occurred. Please press [CHANGE ARM] to change to another mode.]".formatted(Formatting.DARK_RED));
    }

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if ( mode == 0.1 ) {}
		else if ( mode == 0.2 ) {
			Vec3d savedVelocity = user.getVelocity();

			double x = savedVelocity.getX();
			double y = savedVelocity.getY();
			double z = savedVelocity.getZ();


		}
		else if ( mode == 0.3 ) {
			for (int i = 0; i < 4 * 20; i++) {
				if (Rosel.ticks != 0) {
					int level = 0;

					LivingEntity target = findClosestEntities(user, world, 4 /* we will change this to level as soon as we get that working */).get(level);

					target.damage(RoselDamageSources.CONDUCTED, 2);
					spawnParticleOnLine(world, user.getPos(), target.getPos(), 20);

					Rosel.ticks = 20;
					level++;
				}
			}
		}

		return super.use(world, user, hand);
	}

	public List<LivingEntity> findClosestEntities(PlayerEntity player, World world, int maxResults) {
		Vec3d playerPos = player.getPos();

		return world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(50), entity -> entity != player).stream()
			.sorted(Comparator.comparingDouble(entity -> entity.squaredDistanceTo(playerPos)))
			.limit(maxResults)
			.collect(Collectors.toList());
	}

	public void spawnParticleOnLine(World world, Vec3d start, Vec3d end, int particleCount) {
		Vec3d directionn = end.subtract(start).normalize();

		for (int i = 0; i <= particleCount; i++) {
			Vec3d particlePos = start.add(directionn.multiply(i / (double) particleCount));

			if ( RoselConfig.particleTypes == RoselConfig.ParticleTypes.LODESTONE ) {
				ParticleBuilders.create(RoselLodestoneParticles.ROSEL_BOLT)
					.overrideAnimator(SimpleParticleEffect.Animator.WITH_AGE)
					.setScale((.2f + world.random.nextFloat() / 3f))
					.setSpinOffset(world.random.nextFloat() * 360f)
					.setSpin((float) (world.random.nextGaussian() / 100f))
					.setAlpha(0, 0.3f, 0)
					.setAlphaEasing(Easing.QUINTIC_OUT)
					.enableNoClip()
					.setLifetime(5)
					.spawn(world, particlePos.getX(), particlePos.getY(), particlePos.getZ());
			} else {
				world.addParticle(RoselClient.ROSEL_CROSS_V, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0.0, 0.0, 0.0);
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
		} else {
			if ( mode == 0.1 ) {
				tooltip.add(Text.literal("Holding Right-Click allows you to block Projectiles").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("You can §3Parry§r incoming attacks by Punching").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			} else if ( mode == 0.2 ) {
				tooltip.add(Text.literal("Right-Clicking allows you to create §cExplosions").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("More Knockback on Basic Attacks").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			} else if ( mode == 0.3 ) {
				tooltip.add(Text.literal("Charge §6Conduction§r by holding Right-Click").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("Using Charge shoots Bolts of §6Rosel Energy").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			}
		}

		super.appendTooltip(stack, world, tooltip, context);
	}
}
