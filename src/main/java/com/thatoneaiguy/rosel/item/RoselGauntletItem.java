package com.thatoneaiguy.rosel.item;

import com.thatoneaiguy.rosel.RoselClient;
import com.thatoneaiguy.rosel.event.KeyInputHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import xyz.amymialee.mialeemisc.util.MialeeMath;
import xyz.amymialee.mialeemisc.util.MialeeText;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RoselGauntletItem extends BaseRoselWeapon {
	public static double mode = 0.1;

	/*
	* 1 = DEFLECT
	* 2 = SHOCKWAVE
	* 3 = ??? ( shocker )
	*/


	public RoselGauntletItem(QuiltItemSettings settings) {
		super(ToolMaterials.NETHERITE, 3, -2.4F, settings);
	}

	public static double getMode(ItemStack stack) {
		return mode;
	}

	public static String modeConverter() {
		if ( mode == 0.1) {
			return "§3Deflect";
		} else if ( mode == 0.2) {
			return "§cShockwave";
		} else if ( mode == 0.3) {
			return "§6Conductor";
		}
		return ("[An error occurred. Please press [CHANGE ARM] to change to another mode.]".formatted(Formatting.DARK_RED));
    }

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if ( mode == 0.2 ) {
			Vec3d savedVelocity = user.getVelocity();

			double x = savedVelocity.getX();
			double y = savedVelocity.getY();
			double z = savedVelocity.getZ();


		}

		return super.use(world, user, hand);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if ( !Screen.hasShiftDown() ) {
			tooltip.add(Text.literal("Press %s to change modes.".formatted(KeyInputHandler.swapArmKey.getTranslationKey())).formatted(Formatting.DARK_GRAY));
			tooltip.add(Text.literal("Current mode is %s".formatted(modeConverter())).formatted(Formatting.DARK_GRAY));
			tooltip.add(Text.literal(" "));
			tooltip.add(Text.literal("Press §a[SHIFT]§r to show more info").formatted(Formatting.DARK_GRAY));
		} else {
			if ( mode == 0.1 ) {
				tooltip.add(Text.literal("Holding Right-Click allows you to block Projectiles").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("You can §3Parry§r incoming attacks by Punching").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			} else if ( mode == 0.2 ) {
				tooltip.add(Text.literal("Right-Clicking allows you to create §cExplosions").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("More Knockback on Basic Attacks").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			} else if ( mode == 0.3 ) {
				tooltip.add(Text.literal("Charge §6Conduction§r by holding Right-Click").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
				tooltip.add(Text.literal("Using Charge shoots Bolts of §6Rosel Energy").formatted(Formatting.DARK_GRAY));
				tooltip.add(Text.literal(" "));
			}
		}

		super.appendTooltip(stack, world, tooltip, context);
	}
}
