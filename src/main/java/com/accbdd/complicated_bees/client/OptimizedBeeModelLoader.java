package com.accbdd.complicated_bees.client;

import com.accbdd.complicated_bees.genetics.GeneticHelper;
import com.accbdd.complicated_bees.genetics.Species;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.geometry.IUnbakedGeometry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class OptimizedBeeModelLoader implements IGeometryLoader<OptimizedBeeModelLoader.BeeGeometry> {
    public static final ResourceLocation ID = new ResourceLocation(MODID, "optimized_bee_model");
    @Override
    public BeeGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        return new BeeGeometry(deserializationContext.deserialize(jsonObject.get("base_model"), BlockModel.class));
    }

    public record Variant(BeeModel drone, BeeModel princess, BeeModel queen) {
    }

    public static class BeeGeometry implements IUnbakedGeometry<BeeGeometry> {
        private final UnbakedModel model;

        BeeGeometry(UnbakedModel model) {
            this.model = model;
        }

        @Override
        public BakedModel bake(BlockModel blockModel, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation, boolean b) {
            BakedModel bakedModel = model.bake(baker, spriteGetter, modelState, modelLocation);
            return new BeeOverrideModel(bakedModel, baker, modelState, spriteGetter);
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, BlockModel context) {
            model.resolveParents(modelGetter);
        }
    }

    private static class BeeOverrideModel extends BakedModelWrapper<BakedModel>
    {
        private final ItemOverrides overrideList;

        BeeOverrideModel(BakedModel originalModel, ModelBaker baker, ModelState modelState, Function<Material, TextureAtlasSprite> sprites)
        {
            super(originalModel);
            this.overrideList = new BeeOverrideList(baker, modelState, sprites);
        }

        @Override
        public ItemOverrides getOverrides()
        {
            return overrideList;
        }
    }

    private static class BeeModel extends BakedModelWrapper<BakedModel>
    {
        private final List<BakedQuad> quads;

        BeeModel(BakedModel baked, List<BakedQuad> quads)
        {
            super(baked);
            this.quads = quads;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand)
        {
            if (side == null)
            {
                return quads;
            }
            return List.of();
        }
    }

    private static class BeeOverrideList extends ItemOverrides {
        public final HashMap<Species, Variant> cacheMap = new HashMap<>();
        private final ModelBaker baker;
        private final ModelState state;
        private final Function<Material, TextureAtlasSprite> sprites;

        public BeeOverrideList(ModelBaker baker, ModelState state, Function<Material, TextureAtlasSprite> sprites) {
            this.baker = baker;
            this.state = state;
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public BakedModel resolve(BakedModel bakedModel, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
            Species species = GeneticHelper.getSpecies(stack, true);
            cacheMap.computeIfAbsent(species, spec -> {
                BeeModel[] beeModels = new BeeModel[3];
                for (int i = 0; i < 3; i++) {
                    ResourceLocation modelLoc = spec.getModels().get(i);
                    BakedModel bakedModelOverride = baker.bake(modelLoc, state);
                    List<BakedQuad> quads = new ArrayList<>(bakedModelOverride.getQuads(null, null, RandomSource.create()));
                    beeModels[i] = new BeeModel(bakedModel, quads);
                }
                return new Variant(beeModels[0], beeModels[1], beeModels[2]);
            });
            if (stack.is(ItemsRegistration.QUEEN)) {
                return cacheMap.get(species).queen;
            } else if (stack.is(ItemsRegistration.PRINCESS))
                return cacheMap.get(species).princess;
            else
                return cacheMap.get(species).drone;
        }
    }
}
