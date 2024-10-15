package com.thatoneaiguy.rosel.init;

import net.minecraft.entity.damage.DamageSource;

public class RoselDamageSources extends DamageSource {
	public static final  DamageSource INSTAKILL = new RoselDamageSources("instakill").setBypassesArmor().setUnblockable();
	public static final  DamageSource CRYSTALISED = new RoselDamageSources("crystalised").setBypassesArmor().setUnblockable();

	protected RoselDamageSources(String name) {
		super(name);
	}

	public static void register() {}
}
