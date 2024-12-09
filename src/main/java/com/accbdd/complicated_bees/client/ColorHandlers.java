package com.accbdd.complicated_bees.client;

import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.block.entity.BeeNestBlockEntity;
import com.accbdd.complicated_bees.item.BeeItem;
import com.accbdd.complicated_bees.item.CombItem;
import com.accbdd.complicated_bees.registry.BlocksRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

@Environment(EnvType.CLIENT)
public class ColorHandlers {
    public static void registerItemColorHandlers() {
        ColorProviderRegistry.ITEM.register(BeeItem::getItemColor, ItemsRegistration.DRONE.get(), ItemsRegistration.PRINCESS.get(), ItemsRegistration.QUEEN.get());
        ColorProviderRegistry.ITEM.register(CombItem::getItemColor, ItemsRegistration.COMB.get());
        ColorProviderRegistry.ITEM.register(BeeNestBlock::getItemColor, ItemsRegistration.BEE_NEST.get());
    }

    public static void registerBlockColorHandlers() {
        ColorProviderRegistry.BLOCK.register(BeeNestBlockEntity::getNestColor, BlocksRegistration.BEE_NEST.get());
    }
}
