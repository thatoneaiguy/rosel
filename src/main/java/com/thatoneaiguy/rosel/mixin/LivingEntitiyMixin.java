package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntitiyMixin {
	@Shadow
	@Nullable
	public abstract LivingEntity getAttacker();

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
}
