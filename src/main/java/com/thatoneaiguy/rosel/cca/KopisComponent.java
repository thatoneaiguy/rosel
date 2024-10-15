package com.thatoneaiguy.rosel.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.nbt.NbtCompound;

public class KopisComponent implements AutoSyncedComponent, CommonTickingComponent {
	private int attacks = 0;

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
