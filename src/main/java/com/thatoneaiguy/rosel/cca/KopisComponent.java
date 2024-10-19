package com.thatoneaiguy.rosel.cca;

import com.thatoneaiguy.rosel.Rosel;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
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
		return Rosel.ROSEL_COATING_COMPONENT.get(player);
	}

	private void sync() {
		Rosel.ROSEL_COATING_COMPONENT.sync(this.player);
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
		return Math.min(attacks + amount, 9);
	}

	public int decrement(int amount) {
		return Math.max(attacks - amount, 0);
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
