package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;

import javax.swing.text.html.parser.Entity;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
}
