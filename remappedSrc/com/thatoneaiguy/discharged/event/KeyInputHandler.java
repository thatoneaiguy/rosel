package com.thatoneaiguy.discharged.event;

import com.thatoneaiguy.discharged.networking.RoselMessages;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class KeyInputHandler {
	public static final String KEY_CATEGORY_ROSEL = "key.discharged.category.discharged";
	public static final String KEY_SWAP_ARM = "key.discharged.swap_arm";
	public static final String KEY_SWAP_FEEDBACK= "key.discharged.feedback";
	public static final String KEY_SWAP_KNUCKLEBLASTER = "key.discharged.knuckleblaster";
	public static final String KEY_SWAP_WHIPLASH = "key.discharged.whiplash";

	public static KeyBinding swapArmKey;
	public static KeyBinding swapArmDeflect;
	public static KeyBinding swapArmShockwave;
	public static KeyBinding swapArmConductor;

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
		swapArmKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			KEY_SWAP_ARM,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_Z,
			KEY_CATEGORY_ROSEL
		));
		swapArmDeflect = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			KEY_SWAP_FEEDBACK,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));
		swapArmShockwave = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			KEY_SWAP_KNUCKLEBLASTER,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));
		swapArmConductor = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			KEY_SWAP_WHIPLASH,
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			KEY_CATEGORY_ROSEL
		));

		registerInputs();
	}
}
