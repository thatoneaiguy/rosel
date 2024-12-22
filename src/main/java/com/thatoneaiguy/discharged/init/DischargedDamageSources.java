package com.thatoneaiguy.discharged.init;

import net.minecraft.entity.damage.DamageSource;

public class DischargedDamageSources extends DamageSource {
	public static final  DamageSource INSTAKILL = new DischargedDamageSources("instakill").setBypassesArmor().setUnblockable();
	public static final  DamageSource CRYSTALISED = new DischargedDamageSources("crystalised").setBypassesArmor().setUnblockable();

	public static final  DamageSource CONDUCTED = new DischargedDamageSources("conducted");

	protected DischargedDamageSources(String name) {
		super(name);
	}

	public static void register() {}
}
