package net.ghost.cobalt.datagen;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.ModBlocks;
import net.ghost.cobalt.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Cobalt.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.COBALT_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COBALT_ORE.get(),
                        ModBlocks.COBALT_BLOCK.get(),
                        ModBlocks.DEEPSLATE_COBALT_ORE.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COBALT_ORE.get(),
                        ModBlocks.COBALT_BLOCK.get(),
                        ModBlocks.DEEPSLATE_COBALT_ORE.get(),
                        ModBlocks.HEMPCRETE_BRICKS.get()
                );
    }
}
