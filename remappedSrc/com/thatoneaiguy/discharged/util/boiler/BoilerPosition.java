package com.thatoneaiguy.discharged.util.boiler;

import net.minecraft.util.StringIdentifiable;

public enum BoilerPosition implements StringIdentifiable {
	CENTER("center"),
	EDGE("edge"),
	CORNER("corner");

	private final String name;

	BoilerPosition(String name) {
		this.name = name;
	}

	@Override
	public String asString() {
		return this.name;
	}
}
