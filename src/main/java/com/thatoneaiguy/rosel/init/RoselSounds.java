package com.thatoneaiguy.rosel.init;

import com.thatoneaiguy.rosel.Rosel;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RoselSounds {
	Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();

	//	final SoundEvent SOUND_EVENT = createSoundEvent("sound.event.name");
	SoundEvent LUNGE = createSoundEvent("lunge");

	static void register() {
		SOUND_EVENTS.keySet().forEach(soundEvent -> {
			Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent);
		});
	}

	private static SoundEvent createSoundEvent(String path) {
		SoundEvent soundEvent = new SoundEvent(new Identifier(Rosel.MODID, path));
		SOUND_EVENTS.put(soundEvent, new Identifier(Rosel.MODID, path));
		return soundEvent;
	}

}

