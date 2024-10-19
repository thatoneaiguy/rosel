package com.thatoneaiguy.rosel;

import eu.midnightdust.lib.config.MidnightConfig;

public class RoselConfig extends MidnightConfig {
	@Comment(centered = true) public static Comment general;
	@Entry()
	public static float coating_multiplier = 1;
	@Entry
	public static ParticleTypes particleTypes = ParticleTypes.LODESTONE;
	public enum ParticleTypes {
		LODESTONE, VANILLA
	}


	@Comment(centered = true) public static Comment gauntlet;

	@Entry
	public static int ticks = 2;
	@Entry(min = 1, max = 20)
	public static int parry_healing = 4;
	@Entry
	public static GauntletKBType gauntletKBType = GauntletKBType.DIRECTIONAL;
	public enum GauntletKBType {
		DIRECTIONAL, NORMAL, NONE
	}
	@Entry()
	public static boolean gauntlet_offhand = false;


	@Comment(centered = true) public static Comment kopis;

	@Entry(max = 10)
	public static int lunge_cover = 1;
	@Entry
	public static int lunge_cooldown = 40;
	@Entry
	public static int lunge_distance = 25;
	@Entry
	public static int overcharge_cover = 1;

	@Entry
	public static ComboBarLocation comboBarLocation = ComboBarLocation.CROSSHAIR;
	public enum ComboBarLocation {
		CROSSHAIR, HOTBAR
	}
}
