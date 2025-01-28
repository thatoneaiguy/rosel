package com.thatoneaiguy.discharged.util.time;

public interface DelayedAction {
	void executeDelayedAction();

	default void scheduleDelay(int delayTicks) {
		DelayedActionHandler.addDelayedAction(this, delayTicks);
	}
}
