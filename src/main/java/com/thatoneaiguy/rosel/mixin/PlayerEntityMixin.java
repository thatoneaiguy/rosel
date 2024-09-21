package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.item.RoselKapis;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Unique
	int attacks = 0;

	@Unique
	String lastTargetUUID;

	@Inject(method = "attack", at = @At("HEAD"))
	public void rosel$KapisAttackDamageIncreacer(Entity target, CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		ItemStack tool = player.getStackInHand(Hand.MAIN_HAND);

		if ( tool.getItem() instanceof RoselKapis ) {
			if ( target.getUuidAsString().equals(lastTargetUUID))  {
				lastTargetUUID = target.getUuidAsString();

				attacks++;

				System.out.println(attacks);
			} else {
				attacks = 0;
			}
		}
	}
}
