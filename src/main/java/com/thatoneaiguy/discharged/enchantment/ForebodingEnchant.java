package com.thatoneaiguy.discharged.enchantment;

import com.thatoneaiguy.discharged.init.DischargedItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ForebodingEnchant extends Enchantment {

	public ForebodingEnchant() {
		super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.isOf(DischargedItems.ROSEL_GAUNTLET);
	}

	@Override
	public int getMinPower(int level) {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}
}
