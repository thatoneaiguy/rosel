package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.enchantment.LungeEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RoselEnchantments {
	Map<Enchantment, Identifier> ENCHANTMENTS = new LinkedHashMap();
	Enchantment LUNGE = createEnchantment("lunge", new LungeEnchantment());

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
