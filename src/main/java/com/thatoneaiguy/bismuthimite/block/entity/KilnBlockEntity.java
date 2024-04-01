package com.thatoneaiguy.bismuthimite.block.entity;

import com.thatoneaiguy.bismuthimite.init.BismuthimiteBlockEntities;
import com.thatoneaiguy.bismuthimite.init.BismuthimiteItems;
import com.thatoneaiguy.bismuthimite.init.ImplementedInventory;
import com.thatoneaiguy.bismuthimite.screen.KilnScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KilnBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 72;

	public KilnBlockEntity(BlockPos pos, BlockState state) {
		super(BismuthimiteBlockEntities.KILN, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				switch (index) {
					case 0: return KilnBlockEntity.this.progress;
					case 1: return KilnBlockEntity.this.maxProgress;
					default: return 0;
				}
			}

			public void set(int index, int value) {
				switch(index) {
					case 0: KilnBlockEntity.this.progress = value; break;
					case 1: KilnBlockEntity.this.maxProgress = value; break;
				}
			}

			public int size() {
				return 2;
			}
		};
	}

	private void resetProgress() {
		this.progress = 0;
	}

	public static void tick(World world, BlockPos blockPos, BlockState state, KilnBlockEntity entity) {
		if(world.isClient()) {
			return;
		}

		if(hasRecipe(entity)) {
			entity.progress++;
			markDirty(world, blockPos, state);
			if(entity.progress >= entity.maxProgress) {
				craftItem(entity);
			}
		} else {
			entity.resetProgress();
			markDirty(world, blockPos, state);
		}

	}

	private static void craftItem(KilnBlockEntity entity) {

		SimpleInventory inventory = new SimpleInventory(entity.size());
		for (int i = 0; i < entity.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		if(hasRecipe(entity)) {
			entity.removeStack(1, 1);
			entity.setStack(2, new ItemStack(BismuthimiteItems.BISMUTH, entity.getStack(2).getCount() + 1));

			entity.resetProgress();
		}

	}

	private static boolean hasRecipe(KilnBlockEntity entity) {
		SimpleInventory inventory = new SimpleInventory(entity.size());
		for (int i = 0; i < entity.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		boolean hasCopperInFirstSlot = entity.getStack(1).getItem() == Items.COPPER_INGOT;

		return hasCopperInFirstSlot && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, BismuthimiteItems.BISMUTH);
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
		return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
		return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.inventory;
	}

	@Override
	public Text getDisplayName() {
		return Text.literal("Kiln");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity playerEntity) {
		return new KilnScreenHandler(syncId, inv, this, this.propertyDelegate);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt("kiln.progress", progress);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		Inventories.readNbt(nbt, inventory);
		super.readNbt(nbt);
		progress = nbt.getInt("kiln.progress");
	}

}
