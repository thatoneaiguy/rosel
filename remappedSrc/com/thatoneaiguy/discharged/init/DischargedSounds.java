package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import java.util.LinkedHashMap;
import java.util.Map;

public class DischargedSounds {

	public static final SoundEvent BLOCK_TUFF_BRICKS_BREAK = register("block.tuff_bricks.break");
	public static final SoundEvent BLOCK_TUFF_BRICKS_FALL = register("block.tuff_bricks.fall");
	public static final SoundEvent BLOCK_TUFF_BRICKS_HIT = register("block.tuff_bricks.hit");
	public static final SoundEvent BLOCK_TUFF_BRICKS_PLACE = register("block.tuff_bricks.place");
	public static final SoundEvent BLOCK_TUFF_BRICKS_STEP = register("block.tuff_bricks.step");
	public static final SoundEvent BLOCK_POLISHED_TUFF_BREAK = register("block.polished_tuff.break");
	public static final SoundEvent BLOCK_POLISHED_TUFF_FALL = register("block.polished_tuff.fall");
	public static final SoundEvent BLOCK_POLISHED_TUFF_HIT = register("block.polished_tuff.hit");
	public static final SoundEvent BLOCK_POLISHED_TUFF_PLACE = register("block.polished_tuff.place");
	public static final SoundEvent BLOCK_POLISHED_TUFF_STEP = register("block.polished_tuff.step");
	public static final SoundEvent BLOCK_COPPER_BULB_BREAK = register("block.copper_bulb.break");
	public static final SoundEvent BLOCK_COPPER_BULB_STEP = register("block.copper_bulb.step");
	public static final SoundEvent BLOCK_COPPER_BULB_PLACE = register("block.copper_bulb.place");
	public static final SoundEvent BLOCK_COPPER_BULB_HIT = register("block.copper_bulb.hit");
	public static final SoundEvent BLOCK_COPPER_BULB_FALL = register("block.copper_bulb.fall");
	public static final SoundEvent BLOCK_COPPER_BULB_TURN_ON = register("block.copper_bulb.turn_on");
	public static final SoundEvent BLOCK_COPPER_BULB_TURN_OFF = register("block.copper_bulb.turn_off");
	public static final SoundEvent BLOCK_COPPER_DOOR_CLOSE = register("block.copper_door.close");
	public static final SoundEvent BLOCK_COPPER_DOOR_OPEN = register("block.copper_door.open");
	public static final SoundEvent BLOCK_COPPER_GRATE_BREAK = register("block.copper_grate.break");
	public static final SoundEvent BLOCK_COPPER_GRATE_STEP = register("block.copper_grate.step");
	public static final SoundEvent BLOCK_COPPER_GRATE_PLACE = register("block.copper_grate.place");
	public static final SoundEvent BLOCK_COPPER_GRATE_HIT = register("block.copper_grate.hit");
	public static final SoundEvent BLOCK_COPPER_GRATE_FALL = register("block.copper_grate.fall");
	public static final SoundEvent BLOCK_COPPER_TRAPDOOR_CLOSE = register("block.copper_trapdoor.close");
	public static final SoundEvent BLOCK_COPPER_TRAPDOOR_OPEN = register("block.copper_trapdoor.open");
	public static final SoundEvent LUNGE = register("item.rosel_kopis.lunge");
	public static final BlockSoundGroup TUFF_BRICKS;
	public static final BlockSoundGroup POLISHED_TUFF;
	public static final BlockSoundGroup COPPER_BULB;
	public static final BlockSoundGroup COPPER_GRATE;

	public DischargedSounds() {
	}

	private static SoundEvent register(String name) {
		Identifier id = new Identifier("discharged", name);
		return (SoundEvent)Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}

	public static void registerSounds() {
	}

	static {
		TUFF_BRICKS = new BlockSoundGroup(1.0F, 1.0F, BLOCK_TUFF_BRICKS_BREAK, BLOCK_TUFF_BRICKS_STEP, BLOCK_TUFF_BRICKS_PLACE, BLOCK_TUFF_BRICKS_HIT, BLOCK_TUFF_BRICKS_FALL);
		POLISHED_TUFF = new BlockSoundGroup(1.0F, 1.0F, BLOCK_POLISHED_TUFF_BREAK, BLOCK_POLISHED_TUFF_STEP, BLOCK_POLISHED_TUFF_PLACE, BLOCK_POLISHED_TUFF_HIT, BLOCK_POLISHED_TUFF_FALL);
		COPPER_BULB = new BlockSoundGroup(1.0F, 1.0F, BLOCK_COPPER_BULB_BREAK, BLOCK_COPPER_BULB_STEP, BLOCK_COPPER_BULB_PLACE, BLOCK_COPPER_BULB_HIT, BLOCK_COPPER_BULB_FALL);
		COPPER_GRATE = new BlockSoundGroup(1.0F, 1.0F, BLOCK_COPPER_GRATE_BREAK, BLOCK_COPPER_GRATE_STEP, BLOCK_COPPER_GRATE_PLACE, BLOCK_COPPER_GRATE_HIT, BLOCK_COPPER_GRATE_FALL);
	}

}

