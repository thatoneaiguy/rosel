package com.thatoneaiguy.bismuthimite.init;

import com.thatoneaiguy.bismuthimite.Bismuthimite;
import com.thatoneaiguy.bismuthimite.item.DiscItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class BismuthimiteItems {

	public static final ItemGroup BISMUTHIMITE = FabricItemGroupBuilder.build(
		new Identifier(Bismuthimite.MODID, "bismuthimite"), () -> new ItemStack(BismuthimiteBlocks.KILN));

	public static final Item BISMUTH = registerItem("bismuth", new Item(new QuiltItemSettings().rarity(Rarity.UNCOMMON).group(BISMUTHIMITE)));

	public static final Item BISMUTH_DISC = registerItem("bismuth_disc", new DiscItem(new QuiltItemSettings().rarity(Rarity.UNCOMMON).group(BISMUTHIMITE)));

	public static final Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Bismuthimite.MODID, name), item);
	}

	public static void register() {
	}
}
