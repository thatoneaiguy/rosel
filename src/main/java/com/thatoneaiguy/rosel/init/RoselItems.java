package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.item.BaseRoselItem;
import com.thatoneaiguy.rosel.item.DiscItem;
import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import com.thatoneaiguy.rosel.item.RoselKapis;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class RoselItems {
	public static final Item ROSEL_SHARD = registerItem("rosel_shard", new BaseRoselItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON)));
	public static final Item ROSEL_DISC = registerItem("rosel_disc", new DiscItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
	public static final Item ROSEL_KAPIS = registerItem("rosel_kapis", new RoselKapis(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
	public static final Item ROSEL_GAUNTLET = registerItem("rosel_gauntlet", new RoselGauntletItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

	public static final Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Rosel.MODID, name), item);
	}

	public static void register() {}
}
