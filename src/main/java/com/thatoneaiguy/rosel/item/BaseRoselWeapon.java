package com.thatoneaiguy.rosel.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import xyz.amymialee.mialeemisc.util.MialeeText;

public class BaseRoselWeapon extends SwordItem {

	public BaseRoselWeapon(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		return MialeeText.withColor(super.getName(stack), 0xFFE78F);
	}
}
