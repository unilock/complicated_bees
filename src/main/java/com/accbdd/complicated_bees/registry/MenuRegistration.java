package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.screen.AnalyzerMenu;
import com.accbdd.complicated_bees.screen.ApiaryMenu;
import com.accbdd.complicated_bees.screen.CentrifugeMenu;
import com.accbdd.complicated_bees.screen.GeneratorMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class MenuRegistration {
    public static final MenuType<CentrifugeMenu> CENTRIFUGE_MENU = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(MODID, "centrifuge"),
            new ExtendedScreenHandlerType<>((windowId, inv, data) -> new CentrifugeMenu(windowId, inv.player, data.readBlockPos())));
    public static final MenuType<ApiaryMenu> APIARY_MENU = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(MODID, "apiary"),
            new ExtendedScreenHandlerType<>((windowId, inv, data) -> new ApiaryMenu(windowId, inv.player, data.readBlockPos())));
    public static final MenuType<GeneratorMenu> GENERATOR_MENU = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(MODID, "generator"),
            new ExtendedScreenHandlerType<>(((windowId, inv, data) -> new GeneratorMenu(windowId, inv.player, data.readBlockPos()))));
    public static final MenuType<AnalyzerMenu> ANALYZER_MENU = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(MODID, "analyzer"),
            new MenuType<>((windowId, playerInv) -> AnalyzerMenu.fromNetwork(windowId, playerInv), FeatureFlags.VANILLA_SET));

    public static void register() {}
}
