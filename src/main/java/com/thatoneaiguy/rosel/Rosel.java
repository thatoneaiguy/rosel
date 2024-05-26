package com.thatoneaiguy.rosel;

import com.thatoneaiguy.rosel.init.*;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Rosel implements ModInitializer {

	public static final String MODID = "rosel";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final ArrayList<UUID> soulmouldTheft = new ArrayList<>();

	@Override
	public void onInitialize(ModContainer mod) {

		LOGGER.info("fuck i forgot valentines day");

		soulmouldTheft.add(UUID.fromString("9da4f059-fb15-4b34-a2e5-54cfecf7c22e"));

		RoselBlocks.register();

		RoselBlockEntities.register();

		RoselEntities.register();

		RoselItems.register();

	}
}
