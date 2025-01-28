package com.thatoneaiguy.discharged.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.thatoneaiguy.discharged.DischargedConfig;
import com.thatoneaiguy.discharged.cca.KopisComponent;
import com.thatoneaiguy.discharged.cca.RoselCoatingComponent;
import com.thatoneaiguy.discharged.init.DischargedEnchantments;
import com.thatoneaiguy.discharged.init.DischargedSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import xyz.amymialee.mialeemisc.entities.IPlayerTargeting;

import java.util.List;
import java.util.UUID;

public class RoselKopis extends BaseRoselWeapon {
	protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	protected boolean hasSearchedTarget;
	protected Entity lungeTarget;

	public RoselKopis(QuiltItemSettings settings) {
		super(new RoselToolMaterial(), 7, -2.6F, settings);

		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", .5f, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack mainHandStack = user.getMainHandStack();

		boolean hasLunge = EnchantmentHelper.getLevel(DischargedEnchantments.LUNGE, mainHandStack) > 0;

		if (hasLunge) {

			if (!(user instanceof IPlayerTargeting targeting)) {
				return TypedActionResult.fail(mainHandStack);
			}
			Entity target = targeting.mialeeMisc$getLastTarget();

			double range = DischargedConfig.lunge_distance;
			Vec3d playerPos = user.getPos();
			Vec3d playerLook = user.getRotationVec(1.0f);

			double playerX = user.getX();
			double playerY = user.getY();
			double playerZ = user.getZ();

			Vec3d endVec = playerPos.add(playerLook.multiply(range));
			Box box = new Box(playerPos, endVec).expand(1.0, 1.0, 1.0);

			List<Entity> entities = world.getEntitiesByClass(Entity.class, box, (entity) -> entity != user);

			if ( !entities.isEmpty() ) {
				if (!world.isClient) {
					if (!this.hasSearchedTarget) {
						this.lungeTarget = targeting.mialeeMisc$getLastTarget();
					}
					this.hasSearchedTarget = true;

					float pitch = .9f + (user.getRandom().nextFloat() * .2f);
					world.playSound(null, playerX, playerY, playerZ, DischargedSounds.LUNGE, SoundCategory.PLAYERS, 2f, pitch);

					Vec3d targetPos = target.getPos();

					double distanceInFront = 1.5;

					Vec3d direction = targetPos.subtract(playerPos).normalize();

					Vec3d landingPos = targetPos.subtract(direction.multiply(distanceInFront));

					double totalDistance = playerPos.distanceTo(landingPos);

					double timeToReachTarget = totalDistance / 2;

					Vec3d horizontalVelocity = landingPos.subtract(playerPos).multiply(1.0 / timeToReachTarget);

					double gravity = 0.08;
					double verticalVelocity = (landingPos.y - playerPos.y) / timeToReachTarget + 0.5 * gravity * timeToReachTarget;

					Vec3d velocity = new Vec3d(horizontalVelocity.x, verticalVelocity, horizontalVelocity.z);

					user.setVelocity(velocity);
					user.velocityModified = true;

					user.getItemCooldownManager().set(this, DischargedConfig.lunge_cooldown);

					RoselCoatingComponent.get(user).increment(DischargedConfig.lunge_cover);


					return TypedActionResult.success(mainHandStack);
				}
			}
		}
		user.swingHand(hand);
		return TypedActionResult.fail(mainHandStack);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if ( attacker instanceof PlayerEntity player ) {
			KopisComponent.get(player).increment(1);
			RoselCoatingComponent.get(player).increment(1);
		}

		return super.postHit(stack, target, attacker);
	}

	/*
	*
	* 1.3 Attack Speed
	* 12 Attack Damage at MAX starts at 8

	* Base:
	* Reverse Berserk
	* Means that the longer of a combo you have, the more dmg you do
	* + 1 damage per hit

	* OVERCHARGE
	* Increase the cap of dmg from Reverse Berserk
	* Holding it for too long will coat you in Rosel
	* Max damage is 16
	* The more you hit, the more your rosel coating happens

	* LUNGE
	* Select an entity, leap towards them, get covered in rosel the more you lunge
	*/
}
