package com.thatoneaiguy.rosel.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import xyz.amymialee.mialeemisc.util.MialeeMath;

import java.util.Arrays;
import java.util.Objects;

public class RoselGauntletItem extends BaseRoselWeapon {
	public RoselGauntletItem(QuiltItemSettings settings) {
		super(ToolMaterials.NETHERITE, 3, -2.4F, settings);
	}

	public static Action[] getAction(ItemStack stack) {
		NbtCompound compound = stack.getOrCreateNbt();
		return Action.values();
	}

	public static void clamp(ServerPlayerEntity serverPlayerEntity) {
		ItemStack stack = serverPlayerEntity.getMainHandStack();
		NbtCompound compound = stack.getOrCreateNbt();
		compound.putInt("action", MialeeMath.clampLoop(compound.getInt("action") + (serverPlayerEntity.isSneaking() ? -1 : 1), 0, Action.values().length));

	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		/*MinecraftClient client = MinecraftClient.getInstance();
		HitResult hit = client.crosshairTarget;

		switch(hit.getType()) {
			case MISS:
				user.sendMessage(Text.literal("MISS"), true);
				break;
			case BLOCK:
				BlockHitResult blockHit = (BlockHitResult) hit;
				BlockPos blockPos = blockHit.getBlockPos();
				BlockState blockState = client.world.getBlockState(blockPos);
				Block block = blockState.getBlock();
				user.sendMessage(Text.literal("BLOCK: " + block), true);
				break;
			case ENTITY:
				EntityHitResult entityHit = (EntityHitResult) hit;
				Entity entity = entityHit.getEntity();
				user.sendMessage(Text.literal("ENTITY: " + entity), true);
				break;
		}*/

		return super.use(world, user, hand);
	}


	public enum Action {
		WHIPLASH,
		FEEDBACKER,
		KNUCKLEBLASTER
	}
}
