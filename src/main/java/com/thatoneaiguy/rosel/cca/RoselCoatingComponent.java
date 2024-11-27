package com.thatoneaiguy.rosel.cca;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.RoselConfig;
import com.thatoneaiguy.rosel.init.RoselDamageSources;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class RoselCoatingComponent implements AutoSyncedComponent, CommonTickingComponent {
	private final PlayerEntity player;
	int level = 0;
	int ticks = 1;
	int delay = 0;

	public RoselCoatingComponent(PlayerEntity player) {
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
		this.level = buf.readVarInt();
		this.ticks = buf.readVarInt();
		this.delay = buf.readVarInt();
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeVarInt(this.level);
		buf.writeVarInt(this.ticks);
		buf.writeVarInt(this.delay);
	}

	public int getLevel() {
		return level;
	}

	public int increment(int amount) {
		return Math.min(level + amount, 10);
	}

	public int decrement(int amount) {
		return Math.max(level - amount, 0);
	}

	public int reset() {
		level = 0;
		Rosel.ROSEL_COATING_COMPONENT.sync(player);
		return level;
	}
	@Override
	public void tick() {
		delay = 20 - level;
		ticks++;

		if ( ticks == delay ) {
			if ( level != 10 ) {
				delay = 20;
				ticks = 0;
				player.damage(RoselDamageSources.CRYSTALISED, level * RoselConfig.coating_multiplier);
				if ( player.isDead() ) {
					// Summon crystal entity
				}
			} else {
				player.damage(RoselDamageSources.INSTAKILL, 10000);
				delay = 20;
				ticks = 0;
				level = 0;
			}
		}
		this.sync();
	}


	/*@Override
	public void clientTick() {
		if ( client != null ) {
			if (ticks == delay) {
				if (level != 10) {
					for (int i = 0; i < 8; i++) {
						ParticleBuilders.create(RoselParticles.ROSEL_CROSS)
							.overrideAnimator(SimpleParticleEffect.Animator.WITH_AGE)
							.setScale((.2f + player.world.random.nextFloat() / 3f))
							.setSpinOffset(player.world.random.nextFloat() * 360f)
							.setSpin((float) (player.world.random.nextGaussian() / 100f))
							.setAlpha(0, 0.3f, 0)
							.setAlphaEasing(Easing.QUINTIC_IN, Easing.QUINTIC_OUT)
							.enableNoClip()
							.setLifetime(5)
							.randomOffset(2)
							.spawn(player.world, player.getX(), player.getY(), player.getZ());
					}
				}
			}
		}

		this.tick();
	}*/

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.level = tag.getInt("level");
		this.ticks = tag.getInt("ticks");
		this.delay = tag.getInt("delay");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putInt("level", this.level);
		tag.putInt("ticks", this.ticks);
		tag.putInt("delay", this.delay);
	}
}
