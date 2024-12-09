package com.accbdd.complicated_bees.datagen;

import com.accbdd.complicated_bees.client.OptimizedBeeModelBuilder;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BeeModelGenerator extends ModelProvider<OptimizedBeeModelBuilder> {
    public BeeModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, ITEM_FOLDER, OptimizedBeeModelBuilder::new, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ResourceLocation[] bees = {
                ItemsRegistration.PRINCESS.getId(),
                ItemsRegistration.QUEEN.getId(),
                ItemsRegistration.DRONE.getId()
        };

        for (int i = 0; i < bees.length; i++) {
            ItemModelBuilder builder = new ItemModelBuilder(bees[i], existingFileHelper);
            builder.parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", modLoc("item/bee_base"))
                    .texture("layer1", modLoc("item/bee_outline"));
            if (i == 0) {
                builder.texture("layer2", modLoc("item/princess_crown"));
            } else if (i == 1) {
                builder.texture("layer2", modLoc("item/queen_crown"));
            }
            getBuilder(bees[i].toString())
                    .baseModel(builder);
        }
    }

    @Override
    public String getName() {
        return "Bee Models: " + modid;
    }
}
