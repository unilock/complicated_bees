package com.accbdd.complicated_bees;

import com.accbdd.complicated_bees.client.ColorHandlers;
import com.accbdd.complicated_bees.client.OptimizedBeeModelLoader;
import com.accbdd.complicated_bees.particle.BeeParticle;
import com.accbdd.complicated_bees.registry.EntitiesRegistration;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.MenuRegistration;
import com.accbdd.complicated_bees.screen.AnalyzerScreen;
import com.accbdd.complicated_bees.screen.ApiaryScreen;
import com.accbdd.complicated_bees.screen.CentrifugeScreen;
import com.accbdd.complicated_bees.screen.GeneratorScreen;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.geometry.RegisterGeometryLoadersCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ComplicatedBeesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntitiesRegistration.BEE_STAFF_MOUNT, (context) -> new ThrownItemRenderer<>(context, 1.0f, true));

        MenuScreens.register(MenuRegistration.CENTRIFUGE_MENU, CentrifugeScreen::new);
        MenuScreens.register(MenuRegistration.APIARY_MENU, ApiaryScreen::new);
        MenuScreens.register(MenuRegistration.GENERATOR_MENU, GeneratorScreen::new);
        MenuScreens.register(MenuRegistration.ANALYZER_MENU, AnalyzerScreen::new);

        ColorHandlers.registerItemColorHandlers();
        ColorHandlers.registerBlockColorHandlers();

        RegisterGeometryLoadersCallback.EVENT.register(this::registerGeometryLoaders);

        registerParticleProviders();
    }

    public void registerGeometryLoaders(Map<ResourceLocation, IGeometryLoader<?>> map) {
        map.put(OptimizedBeeModelLoader.ID, new OptimizedBeeModelLoader());
    }

    public static void registerParticleProviders() {
        ParticleFactoryRegistry.getInstance().register(EsotericRegistration.BEE_PARTICLE,
                BeeParticle.Provider::new);
    }
}
