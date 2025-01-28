package com.thatoneaiguy.discharged.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import xyz.amymialee.mialeemisc.util.MialeeText;

public class BaseRoselItem extends Item {

	public BaseRoselItem(Settings settings) {
		super(settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		return MialeeText.withColor(super.getName(stack), 0xFFE78F);
	}
}
