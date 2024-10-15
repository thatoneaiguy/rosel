package com.thatoneaiguy.rosel.cca;

import com.thatoneaiguy.rosel.Rosel;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class RoselParryingComponent  implements AutoSyncedComponent, CommonTickingComponent {
	private final PlayerEntity player;
	private MinecraftClient client;
	int ticks = 2;

	public RoselParryingComponent(PlayerEntity player) {
		this.player = player;
	}

	public static RoselParryingComponent get(@NotNull PlayerEntity player) {
		//return Rosel.ROSEL_PARRY_COMPONENT.get(player);
        return null;
    }

	private void sync() {
	//	Rosel.ROSEL_PARRY_COMPONENT.sync(this.player);
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
