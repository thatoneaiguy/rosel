package com.thatoneaiguy.rosel.item;

import com.thatoneaiguy.rosel.init.RoselItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import xyz.amymialee.mialeemisc.util.MialeeText;

public class RoselKapis extends BaseRoselWeapon {
	public RoselKapis(QuiltItemSettings settings) {
		super(ToolMaterials.NETHERITE, 5, -2.7F, settings);
	}

	/*
	*
	* 1.3 Attack Speed
	* x Attack Damage
	*
	* Base:
	* Reverse Berserk
	* Means that the longer of a combo you have, the more dmg you do
	*
	* OVERCHARGE
	* Increase the cap of dmg from Reverse Berserk
	* Holding it for too long will coat you in Rosel
	*
	* DASH?
	* Select an entity, after about 1 second of not being able to attack, leap towards them
	*
	 */
}
