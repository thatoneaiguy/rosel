package com.thatoneaiguy.discharged.cca;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.entity.DiscEntity;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class DiscComponent implements AutoSyncedComponent, CommonTickingComponent {
	private final DiscEntity disc;

	private boolean accumulated;
	private boolean spewed;
	private boolean frost;
	private boolean brimstone;

	public DiscComponent(DiscEntity disc) {
		this.disc = disc;
	}

	public static DiscComponent get(@NotNull DiscEntity disc) {
		return Discharged.ROSEL_DISC_COMPONENT.get(disc);
	}

	private void sync() {
		Discharged.ROSEL_DISC_COMPONENT.sync(this.disc);
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		this.accumulated = buf.readBoolean();
		this.brimstone = buf.readBoolean();
		this.frost = buf.readBoolean();
		this.spewed = buf.readBoolean();
	}

	public boolean isAccumulated() {
		return accumulated;
	}

	public void swapAccumulate() {
		accumulated = !accumulated;
	}

	public boolean isSpewed() {
		return spewed;
	}

	public void swapSpew() {
		spewed = !spewed;
	}

	public boolean isFrost() {
		return frost;
	}

	public void swapFrost() {
		frost = !frost;
	}

	public boolean isBrimstone() {
		return brimstone;
	}

	public void swapBrimstone() {
		brimstone = !brimstone;
	}



	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeBoolean(this.accumulated);
		buf.writeBoolean(this.brimstone);
		buf.writeBoolean(this.frost);
		buf.writeBoolean(this.spewed);
	}

	@Override
	public void tick() {

	}

	@Override
	public void readFromNbt(NbtCompound tag) {

	}

	@Override
	public void writeToNbt(NbtCompound tag) {

	}
}
