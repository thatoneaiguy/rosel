package com.thatoneaiguy.rosel.networking;

import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.packet.ArmChangeC2SPacket;
import com.thatoneaiguy.rosel.packet.ArmChangeToConductorC2SPacket;
import com.thatoneaiguy.rosel.packet.ArmChangeToDeflectC2SPacket;
import com.thatoneaiguy.rosel.packet.ArmChangeToShockwaveC2SPacket;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class RoselMessages {
	public static final Identifier SWAP_ARMS_ID = new Identifier(Rosel.MODID, "swap_arm");

	public static final Identifier SWAP_ARMS_DIRECT_DEFLECT = new Identifier(Rosel.MODID, "swap_arm_deflect");
	public static final Identifier SWAP_ARMS_DIRECT_SHOCKWAVE = new Identifier(Rosel.MODID, "swap_arm_shockwave");
	public static final Identifier SWAP_ARMS_DIRECT_CONDUCTOR = new Identifier(Rosel.MODID, "swap_arm_conductor");


	public static void registerC2S() {
		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_ID, ArmChangeC2SPacket::receive);

		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_DEFLECT, ArmChangeToDeflectC2SPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_SHOCKWAVE, ArmChangeToShockwaveC2SPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_CONDUCTOR, ArmChangeToConductorC2SPacket::receive);
	}

	public static void registerS2C() {

	}
}
