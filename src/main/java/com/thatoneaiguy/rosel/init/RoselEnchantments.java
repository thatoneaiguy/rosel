package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.enchantment.ForebodingEnchant;
import com.thatoneaiguy.rosel.enchantment.LungeEnchantment;
import com.thatoneaiguy.rosel.enchantment.OverchargeEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RoselEnchantments {
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
		ENCHANTMENTS.put(enchantment, Rosel.id(name));
		return enchantment;
	}
}
