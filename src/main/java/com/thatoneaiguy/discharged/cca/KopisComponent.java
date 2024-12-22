package com.thatoneaiguy.discharged.cca;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.init.DischargedEnchantments;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class KopisComponent implements AutoSyncedComponent {
	private int attacks = 0;
	private final PlayerEntity player;

	public KopisComponent(PlayerEntity player) {
		this.player = player;
	}

	public static RoselCoatingComponent get(@NotNull PlayerEntity player) {
		return Discharged.ROSEL_COATING_COMPONENT.get(player);
	}

	private void sync() {
		Discharged.ROSEL_COATING_COMPONENT.sync(this.player);
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		this.attacks = buf.readVarInt();
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeVarInt(this.attacks);
	}

	public int getAttacks() {
		return attacks;
	}

	public int increment(int amount) {
		ItemStack mainHandStack = player.getMainHandStack();
		boolean hasOvercharge = EnchantmentHelper.getLevel(DischargedEnchantments.OVERCHARGE, mainHandStack) > 0;

		if ( hasOvercharge ) {
			if ( attacks + amount >= 9) return attacks = attacks + amount;
			if ( attacks + amount < 9 ) return 9;
		} else {
			if ( attacks + amount >= 5) return attacks = attacks + amount;
			if ( attacks + amount < 5 ) return 5;
		}

		return 0;
	}

	public int decrement(int amount) {
		ItemStack mainHandStack = player.getMainHandStack();
		boolean hasOvercharge = EnchantmentHelper.getLevel(DischargedEnchantments.OVERCHARGE, mainHandStack) > 0;

		if ( hasOvercharge ) {
			if ( attacks - amount >= 9) return attacks = attacks - amount;
			if ( attacks - amount < 9 ) return 9;
		} else {
			if ( attacks - amount >= 5) return attacks = attacks - amount;
			if ( attacks - amount < 5 ) return 5;
		}

		return 0;
	}

	public int reset() {
		attacks = 0;
		return attacks;
	}
	@Override
	public void readFromNbt(NbtCompound tag) {

	}

	@Override
	public void writeToNbt(NbtCompound tag) {

	}
}
