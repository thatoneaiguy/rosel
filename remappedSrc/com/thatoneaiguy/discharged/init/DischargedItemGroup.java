package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class DischargedItemGroup {
	public static final ItemGroup ROSEL_MAIN_ITEM_GROUP =
		QuiltItemGroup.builder(new Identifier(Discharged.MODID, "dischargedmain"))
			.icon(DischargedItems.ROSEL_SHARD::getDefaultStack)
			.displayText(Text.literal("Discharged"))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(DischargedItems.ROSEL_KOPIS));
				//stacks.add(new ItemStack(RoselItems.ROSEL_DISC));
				stacks.add(new ItemStack(DischargedItems.ROSEL_GAUNTLET));
				stacks.add(new ItemStack(DischargedItems.ROSEL_SHARD));
				stacks.add(new ItemStack(DischargedBlocks.PERFECT_ROSEL));
				stacks.add(new ItemStack(DischargedBlocks.RAW_ROSEL));
				stacks.add(new ItemStack(DischargedBlocks.ROUGH_ROSEL));
			})
			.build();

	public static void register() {
	}
}
