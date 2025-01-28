package com.thatoneaiguy.discharged.util.boiler;

import net.minecraft.util.StringIdentifiable;

public enum BoilerShape implements StringIdentifiable {
	NONE("none"),
	COLUMN("column"),
	SQUARE("square"),
	TOWER("tower");

	private final String name;

	BoilerShape(String name) {
		this.name = name;
	}

	@Override
	public String asString() {
		return this.name;
	}
}
