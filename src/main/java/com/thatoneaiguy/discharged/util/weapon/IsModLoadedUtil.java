package com.thatoneaiguy.discharged.util.weapon;

import org.quiltmc.loader.api.QuiltLoader;

public class IsModLoadedUtil {
	public boolean pickYourPoison() {
		return QuiltLoader.isModLoaded("");
	}
	public boolean enchancement() {
		return QuiltLoader.isModLoaded("enchancement");
	}
	public boolean amarite() {
		return QuiltLoader.isModLoaded("amarite");
	}
	public boolean arsenal() {
		return QuiltLoader.isModLoaded("arsenal");
	}
}
