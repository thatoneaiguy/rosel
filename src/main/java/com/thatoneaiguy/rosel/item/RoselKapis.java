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
		super(ToolMaterials.NETHERITE, 8, -2.7F, settings);
	}
}
