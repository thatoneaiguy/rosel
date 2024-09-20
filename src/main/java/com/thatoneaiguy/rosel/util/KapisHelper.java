package com.thatoneaiguy.rosel.util;

import net.minecraft.nbt.NbtCompound;

public class KapisHelper {
	public static int addHits(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int hits = nbt.getInt("kapis_hits");

		if ( hits + amount >= 6 ) {
			hits = 6;
		} else {
			hits += amount;
		}

		nbt.putInt("kapis_hits", hits);
		// datasync
		return hits;
	}

	public static int removeHits(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int hits = nbt.getInt("kapis_hits");

		if ( hits + amount >= 0 ) {
			hits = 0;
		} else {
			hits += amount;
		}

		nbt.putInt("kapis_hits", hits);
		// datasync
		return hits;
	}
}
