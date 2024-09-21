package com.thatoneaiguy.rosel.item;

import net.minecraft.item.ToolMaterials;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

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
