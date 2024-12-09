package com.accbdd.complicated_bees.screen.slot;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlotItemHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemSlot extends SlotItemHandler {
    private final Item item;

    public ItemSlot(ItemStackHandler itemHandler, int index, int xPosition, int yPosition, Item item) {
        super(itemHandler, index, xPosition, yPosition);
        this.item = item;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(item);
    }
}
