package net.ghost.cobalt.datagen;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Cobalt.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.COBALT_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockWithItem(ModBlocks.COBALT_ORE);
        blockWithItem(ModBlocks.HEMPCRETE_BRICKS);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
