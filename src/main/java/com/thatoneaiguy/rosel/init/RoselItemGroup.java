package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.init.RoselItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RoselItemGroup {
	public static final ItemGroup ROSEL_MAIN_ITEM_GROUP = FabricItemGroupBuilder.create(
			new Identifier(Rosel.MODID, "roselmain"))
		.icon(() -> new ItemStack(RoselItems.ROSEL_SHARD))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(RoselItems.ROSEL_KAPIS));
			//stacks.add(new ItemStack(RoselItems.ROSEL_DISC));
			//stacks.add(new ItemStack(RoselItems.ROSEL_GAUNTLET));
			stacks.add(new ItemStack(RoselItems.ROSEL_SHARD));
			stacks.add(new ItemStack(RoselBlocks.PERFECT_ROSEL));
			stacks.add(new ItemStack(RoselBlocks.RAW_ROSEL));
			stacks.add(new ItemStack(RoselBlocks.ROUGH_ROSEL));
		})
		.build();

	public static void register() {
	}
}
