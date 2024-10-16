package com.accbdd.complicated_bees;

import com.accbdd.complicated_bees.client.BeeModel;
import com.accbdd.complicated_bees.particle.BeeParticle;
import com.accbdd.complicated_bees.registry.EntitiesRegistration;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.MenuRegistration;
import com.accbdd.complicated_bees.screen.AnalyzerScreen;
import com.accbdd.complicated_bees.screen.ApiaryScreen;
import com.accbdd.complicated_bees.screen.CentrifugeScreen;
import com.accbdd.complicated_bees.screen.GeneratorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ComplicatedBeesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntitiesRegistration.BEE_STAFF_MOUNT.get(), (context) -> new ThrownItemRenderer<>(context, 1.0f, true));

        MenuScreens.register(MenuRegistration.CENTRIFUGE_MENU.get(), CentrifugeScreen::new);
        MenuScreens.register(MenuRegistration.APIARY_MENU.get(), ApiaryScreen::new);
        MenuScreens.register(MenuRegistration.GENERATOR_MENU.get(), GeneratorScreen::new);
        MenuScreens.register(MenuRegistration.ANALYZER_MENU.get(), AnalyzerScreen::new);

        registerParticleProviders();
    }

    @SubscribeEvent
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(BeeModel.Loader.ID.getPath(), BeeModel.Loader.INSTANCE);
    }

    public static void registerParticleProviders() {
        ParticleFactoryRegistry.getInstance().register(EsotericRegistration.BEE_PARTICLE.get(),
                BeeParticle.Provider::new);
    }
}
