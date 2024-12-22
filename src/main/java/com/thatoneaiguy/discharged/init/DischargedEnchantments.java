package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.enchantment.ForebodingEnchant;
import com.thatoneaiguy.discharged.enchantment.LungeEnchantment;
import com.thatoneaiguy.discharged.enchantment.OverchargeEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface DischargedEnchantments {
	Map<Enchantment, Identifier> ENCHANTMENTS = new LinkedHashMap();
	// Kopis
	Enchantment LUNGE = createEnchantment("lunge", new LungeEnchantment());
	Enchantment OVERCHARGE = createEnchantment("overcharge", new OverchargeEnchantment());
	// Gauntlet
	Enchantment FOREBODING = createEnchantment("foreboding", new ForebodingEnchant());

	static void register() {
		ENCHANTMENTS.keySet().forEach((enchantment) -> {
			Registry.register(Registry.ENCHANTMENT, (Identifier)ENCHANTMENTS.get(enchantment), enchantment);
		});
	}

	static <T extends Enchantment> T createEnchantment(String name, T enchantment) {
		ENCHANTMENTS.put(enchantment, Discharged.id(name));
		return enchantment;
	}
}
