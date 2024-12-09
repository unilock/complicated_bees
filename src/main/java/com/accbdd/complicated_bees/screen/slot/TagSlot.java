package com.accbdd.complicated_bees.screen.slot;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlotItemHandler;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TagSlot extends SlotItemHandler {
    private final TagKey<Item> tag;

    public TagSlot(ItemStackHandler itemHandler, int index, int xPosition, int yPosition, TagKey<Item> tag) {
        super(itemHandler, index, xPosition, yPosition);
        this.tag = tag;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(tag);
    }
}
