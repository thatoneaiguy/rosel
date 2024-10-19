package com.thatoneaiguy.rosel.event;

import com.mojang.blaze3d.platform.InputUtil;
import com.thatoneaiguy.rosel.networking.RoselMessages;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class KeyInputHandler {
	public static final String KEY_CATEGORY_ROSEL = "key.rosel.category.rosel";
	public static final String KEY_SWAP_ARM = "key.rosel.swap_arm";
	public static final String KEY_SWAP_FEEDBACK= "key.rosel.feedback";
	public static final String KEY_SWAP_KNUCKLEBLASTER = "key.rosel.knuckleblaster";
	public static final String KEY_SWAP_WHIPLASH = "key.rosel.whiplash";

	public static KeyBind swapArmKey;
	public static KeyBind swapArmDeflect;
	public static KeyBind swapArmShockwave;
	public static KeyBind swapArmConductor;

	public static void registerInputs() {
		ClientTickEvents.END.register(client -> {
			if (swapArmKey.isPressed()) {
				ClientPlayNetworking.send(RoselMessages.SWAP_ARMS_ID, PacketByteBufs.create());
			}
		});
		ClientTickEvents.END.register(client -> {
			if (swapArmDeflect.isPressed()) {
				ClientPlayNetworking.send(RoselMessages.SWAP_ARMS_DIRECT_DEFLECT, PacketByteBufs.create());
			}
		});
		ClientTickEvents.END.register(client -> {
			if (swapArmShockwave.isPressed()) {
				ClientPlayNetworking.send(RoselMessages.SWAP_ARMS_DIRECT_SHOCKWAVE, PacketByteBufs.create());
			}
		});
		ClientTickEvents.END.register(client -> {
			if (swapArmConductor.isPressed()) {
				ClientPlayNetworking.send(RoselMessages.SWAP_ARMS_DIRECT_CONDUCTOR, PacketByteBufs.create());
			}
		});
	}

	public static void register() {
		swapArmKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
			KEY_SWAP_ARM,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_Z,
			KEY_CATEGORY_ROSEL
		));
		swapArmDeflect = KeyBindingHelper.registerKeyBinding(new KeyBind(
			KEY_SWAP_FEEDBACK,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));
		swapArmShockwave = KeyBindingHelper.registerKeyBinding(new KeyBind(
			KEY_SWAP_KNUCKLEBLASTER,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));
		swapArmConductor = KeyBindingHelper.registerKeyBinding(new KeyBind(
			KEY_SWAP_WHIPLASH,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));

		registerInputs();
	}
}
