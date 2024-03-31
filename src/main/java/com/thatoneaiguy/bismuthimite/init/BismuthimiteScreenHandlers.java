package com.thatoneaiguy.bismuthimite.init;

import com.thatoneaiguy.bismuthimite.screen.KilnScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class BismuthimiteScreenHandlers {

	public static ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;

	public static void register() {
		KILN_SCREEN_HANDLER = new ScreenHandlerType<>(KilnScreenHandler::new);
	}

}
