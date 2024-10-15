package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.RoselConfig;
import com.thatoneaiguy.rosel.init.RoselItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Shadow
	public abstract void startFallFlying();

	@Inject(method = "equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
	public void rosel$noGauntletOffhand(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
		if ( slot == EquipmentSlot.OFFHAND ) {
			if ( stack.isOf(RoselItems.ROSEL_GAUNTLET) ) {
				ci.cancel();
			}
		}
	}
}
