package com.accbdd.complicated_bees.client;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OptimizedBeeModelBuilder extends ModelBuilder<OptimizedBeeModelBuilder> {
    ItemModelBuilder baseModel;

    public OptimizedBeeModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
    }

    public OptimizedBeeModelBuilder baseModel(ItemModelBuilder model) {
        this.baseModel = model;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = new JsonObject();
        JsonObject base_model = baseModel.toJson();
        root.add("base_model", base_model);
        root.addProperty("loader", OptimizedBeeModelLoader.ID.toString());
        return root;
    }
}
