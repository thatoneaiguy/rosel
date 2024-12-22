package com.thatoneaiguy.discharged.util;

public interface DelayedAction {
	void executeDelayedAction();

	default void scheduleDelay(int delayTicks) {
		DelayedActionHandler.addDelayedAction(this, delayTicks);
	}
}
