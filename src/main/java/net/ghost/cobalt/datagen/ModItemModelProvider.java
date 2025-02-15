package net.ghost.cobalt.datagen;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cobalt.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.COBALT_INGOT);
        simpleItem(ModItems.RAW_COBALT);
        simpleItem(ModItems.IRON_CHUNK);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.HEMPCRETE_BRICK);
        simpleItem(ModItems.HEMP);
        simpleItem(ModItems.HEMP_SEEDS);
        simpleItem(ModItems.PEANUT);
        simpleItem(ModItems.PEANUT_SEEDS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Cobalt.MOD_ID, "item/" + item.getId().getPath()));
    }
}
