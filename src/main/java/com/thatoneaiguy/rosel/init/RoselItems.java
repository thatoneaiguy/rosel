package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.item.DiscItem;
import com.thatoneaiguy.rosel.item.RoselKapis;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class RoselItems {

	public static final ItemGroup ROSEL_GROUP = FabricItemGroupBuilder.build(
		new Identifier(Rosel.MODID, "rosel_group"), () -> new ItemStack(RoselItems.ROSEL));

	public static final Item ROSEL = registerItem("rosel", new Item(new QuiltItemSettings().rarity(Rarity.UNCOMMON).group(ROSEL_GROUP)));

	public static final Item ROSEL_DISC = registerItem("rosel_disc", new DiscItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).group(ROSEL_GROUP)));
	public static final Item ROSEL_KAPIS = registerItem("rosel_kapis", new RoselKapis(new QuiltItemSettings().rarity(Rarity.UNCOMMON).group(ROSEL_GROUP)));

	public static final Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Rosel.MODID, name), item);
	}

	public static void register() {
	}
}
