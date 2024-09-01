package com.thatoneaiguy.rosel.networking;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.packet.ExampleC2SPacket;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class RoselMessages {
	public static final Identifier SWAP_ARMS_ID = new Identifier(Rosel.MODID, "swap_arm");
	public static final Identifier SWAP_SYNC_ID = new Identifier(Rosel.MODID, "swap_sync");

	public static void registerC2S() {
		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_ID, ExampleC2SPacket::receive);
	}

	public static void registerS2C() {

	}
}
