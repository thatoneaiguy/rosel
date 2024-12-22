package com.thatoneaiguy.discharged.mixin;

import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {
	@Shadow
	@Final
	public DefaultedList<Slot> slots;

	@Inject(method = "internalOnSlotClick", at = @At("HEAD"), cancellable = true)
	private void discharged$toggleMode(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
		if (slotIndex >= 0 && slotIndex < this.slots.size()) {
			if (button == 1) {
				Slot slot = this.slots.get(slotIndex);
				ItemStack stack = slot.getStack();

				if (stack.getItem() instanceof RoselGauntletItem gauntletItem) {
					gauntletItem.toggleMode(stack);
					player.playSound(SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 0.9f, 1.5f);
					ci.cancel();
				}
			}
		}
	}
}
