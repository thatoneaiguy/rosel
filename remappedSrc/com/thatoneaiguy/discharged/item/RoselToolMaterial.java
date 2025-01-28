package com.thatoneaiguy.discharged.item;

import com.thatoneaiguy.discharged.init.DischargedItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class RoselToolMaterial implements ToolMaterial {
	public static final RoselToolMaterial INSTANCE = new RoselToolMaterial();

	@Override
	public int getDurability() {
		return 455;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 5.0F;
	}

	@Override
	public float getAttackDamage() {
		return 0F;
	}

	@Override
	public int getMiningLevel() {
		return 1;
	}

	@Override
	public int getEnchantability() {
		return 15;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(DischargedItems.ROSEL_SHARD);
	}
}
