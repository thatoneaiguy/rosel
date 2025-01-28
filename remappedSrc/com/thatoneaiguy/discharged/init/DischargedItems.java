package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.item.BaseRoselItem;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import com.thatoneaiguy.discharged.item.RoselKopis;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class DischargedItems {
	public static final Item ROSEL_SHARD = registerItem("rosel_shard", new BaseRoselItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON)));
	//public static final Item ROSEL_DISC = registerItem("rosel_disc", new DiscItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
	public static final Item ROSEL_KOPIS = registerItem("rosel_kopis", new RoselKopis(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
	public static final Item ROSEL_GAUNTLET = registerItem("rosel_gauntlet", new RoselGauntletItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

	public static final Item REALLY_FUCKING_SHITTY_COPPER = registerItem("really_fucking_shitty_copper", new Item(new QuiltItemSettings()));

	public static final Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Discharged.MODID, name), item);
	}

	public static void register() {}
}
