package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.cca.RoselParryingComponent;
import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
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

	@ModifyVariable(at = @At("HEAD"), method = "takeKnockback(DDD)V", ordinal = 0, argsOnly = true)
	private double rosel$dealHarshKB(double strength) {
		LivingEntity attacker = getAttacker();
		ItemStack tool = attacker != null ? attacker.getStackInHand(attacker.getActiveHand()) : ItemStack.EMPTY;

		if (tool.getItem() instanceof RoselGauntletItem) {
			return strength * 6.f;
		} else {
			return strength;
		}
	}

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
