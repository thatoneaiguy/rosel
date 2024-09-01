package com.thatoneaiguy.rosel.packet;

import com.thatoneaiguy.rosel.init.RoselFluids;
import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.quiltmc.qsl.networking.api.PacketSender;

public class ExampleC2SPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
							   PacketByteBuf buf, PacketSender responseSender) {

		RoselGauntletItem.clamp(player);
	}
}
