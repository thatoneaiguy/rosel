package com.thatoneaiguy.rosel.packet;

import com.thatoneaiguy.rosel.item.RoselGauntletItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class ArmChangeC2SPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
							   PacketByteBuf buf, PacketSender responseSender) {
		player.resetLastAttackedTicks();

		if ( RoselGauntletItem.mode == 0.1 ) {
			RoselGauntletItem.mode = 0.2;
		} else if ( RoselGauntletItem.mode == 0.2 ) {
			RoselGauntletItem.mode = 0.3;
		} else if ( RoselGauntletItem.mode == 0.3 ) {
			RoselGauntletItem.mode = 0.1;
		}
	}
}
