package net.ghost.cobalt.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;

public class OreExtractorMKOneRecipe implements Recipe<Container> {
    private final Block inputBlock;
    private final BlockState inputBlockState;
    private final ItemStack outputItem;

    public OreExtractorMKOneRecipe(Block inputBlock, BlockState inputBlockState, ItemStack outputItem) {
        this.inputBlock = inputBlock;
        this.inputBlockState = inputBlockState;
        this.outputItem = outputItem;
    }

// Check Create Above and Beyond's Rubber Recipe, it might be similar to this. Also look at mods like Industrial Foregoing
    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        BlockPos craftingPos = pLevel.getBlockState(pContainer); // Get the position of the crafting block
        BlockPos belowPos = craftingPos.below(); // Position directly below the crafting block

        // Check if the block below is Iron Ore (you can adjust this condition as needed)
        BlockState belowBlockState = pLevel.getBlockState(belowPos);
        if (belowBlockState.getBlock() == Blocks.IRON_ORE) {
            return inputItems.get(0).test(pContainer.getItem(0));
        }

        return false; // Block below is not Iron Ore, so no match
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
