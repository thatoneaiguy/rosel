package com.thatoneaiguy.discharged.networking;

import com.thatoneaiguy.discharged.Discharged;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class RoselMessages {
	public static final Identifier SWAP_ARMS_ID = new Identifier(Discharged.MODID, "swap_arm");

	public static final Identifier SWAP_ARMS_DIRECT_DEFLECT = new Identifier(Discharged.MODID, "swap_arm_deflect");
	public static final Identifier SWAP_ARMS_DIRECT_SHOCKWAVE = new Identifier(Discharged.MODID, "swap_arm_shockwave");
	public static final Identifier SWAP_ARMS_DIRECT_CONDUCTOR = new Identifier(Discharged.MODID, "swap_arm_conductor");


	public static void registerC2S() {
//		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_ID, ArmChangeC2SPacket::receive);
//
//		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_DEFLECT, ArmChangeToDeflectC2SPacket::receive);
//		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_SHOCKWAVE, ArmChangeToShockwaveC2SPacket::receive);
//		ServerPlayNetworking.registerGlobalReceiver(SWAP_ARMS_DIRECT_CONDUCTOR, ArmChangeToConductorC2SPacket::receive);
	}

	public static void registerS2C() {

	}
}
