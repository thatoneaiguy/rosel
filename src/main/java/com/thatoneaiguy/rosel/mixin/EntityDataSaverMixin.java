package com.thatoneaiguy.rosel.mixin;

import com.thatoneaiguy.rosel.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
	@Unique
	private NbtCompound persistentData;
	@Override
	public NbtCompound getPersistentData() {
		if ( this.persistentData == null ) {
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}
	@Inject(method = "writeNbt", at = @At("HEAD"))
	protected void rosel$writeNBT(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
		if ( persistentData != null ) {
			nbt.put("rosel.kapis_hits", persistentData);
		}
	}
	@Inject(method = "readNbt", at = @At("HEAD"))
	protected void rosel$readNBT(NbtCompound nbt, CallbackInfo ci) {
		if ( nbt.contains("rosel.kapis_hits", 10)) {
			persistentData = nbt.getCompound("rosel.kapis_hits");
		}
	}
}
