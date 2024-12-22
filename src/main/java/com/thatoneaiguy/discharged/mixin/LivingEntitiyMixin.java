package com.thatoneaiguy.discharged.mixin;

import com.thatoneaiguy.discharged.DischargedConfig;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntitiyMixin {
	@Shadow
	@Nullable
	public abstract LivingEntity getAttacker();

	@Shadow
	public abstract float getHealth();

	@Shadow
	public abstract boolean isDead();

//	@ModifyVariable(at = @At("HEAD"), method = "takeKnockback(DDD)V", ordinal = 0, argsOnly = true)
//	private double rosel$dealHarshKB(double strength) {
//		LivingEntity entity = (LivingEntity)(Object)this;
//
//		if ( DischargedConfig.gauntletKBType != DischargedConfig.GauntletKBType.NONE ) {
//			LivingEntity attacker = getAttacker();
//			ItemStack tool = attacker != null ? attacker.getStackInHand(attacker.getActiveHand()) : ItemStack.EMPTY;
//
//			if (tool.getItem() instanceof RoselGauntletItem) {
//				if ( RoselGauntletItem.mode == 0.2 ) {
//
//					if ( DischargedConfig.gauntletKBType == DischargedConfig.GauntletKBType.NORMAL ) {
//						return strength * 2.0f;
//					} else if ( DischargedConfig.gauntletKBType == DischargedConfig.GauntletKBType.DIRECTIONAL) {
//						if (attacker != null) {
//							Vec3d lookDirection = attacker.getRotationVec(2.0F);
//
//							double knockbackX = lookDirection.x * strength * 2.0F;
//
//							double knockbackY = lookDirection.y * strength * 2.0F;
//							if (knockbackY < 0.3) {
//								knockbackY = 0.3 * strength * 2.0F;
//							}
//
//							double knockbackZ = lookDirection.z * strength * 6.0F;
//
//							entity.setVelocity(knockbackX, knockbackY, knockbackZ);
//							return strength * 2.0F;
//						}
//					}
//				}
//
//			} else {
//				return strength;
//			}
//		}
//        return strength;
//    }

	@Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"))
	public void rosel$parry(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity attacker = getAttacker();

		//RoselParryingComponent.get().tick();

		// ticks
		// if punch in time while holding the thing
		// return dmg to attacker
		// heal
	}
}
