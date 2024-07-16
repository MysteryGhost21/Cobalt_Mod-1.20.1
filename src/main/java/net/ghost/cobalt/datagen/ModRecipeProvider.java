package net.ghost.cobalt.datagen;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.ModBlocks;
import net.ghost.cobalt.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> COBALT_SMELTABLES = List.of(ModItems.RAW_COBALT.get(),
    ModBlocks.COBALT_ORE.get(),
    ModBlocks.DEEPSLATE_COBALT_ORE.get());



    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT.get(), 0.25f, 100, "cobalt");
        oreSmelting(pWriter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT.get(), 0.25f, 200, "cobalt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COBALT_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.COBALT_INGOT.get())
                .unlockedBy(getHasName(ModItems.COBALT_INGOT.get()), has(ModItems.COBALT_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HEMPCRETE_BRICKS.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', ModItems.HEMPCRETE_BRICK.get())
                .unlockedBy(getHasName(ModItems.HEMPCRETE_BRICK.get()), has(ModItems.HEMPCRETE_BRICK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COBALT_INGOT.get(), 9)
                .requires(ModBlocks.COBALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.COBALT_BLOCK.get()), has(ModBlocks.COBALT_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PEANUT_SEEDS.get(), 1)
                .requires(ModItems.PEANUT.get())
                .unlockedBy(getHasName(ModItems.PEANUT.get()), has(ModItems.PEANUT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HEMP_SEEDS.get(), 1)
                .requires(ModItems.HEMP.get())
                .unlockedBy(getHasName(ModItems.HEMP.get()), has(ModItems.HEMP.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HEMPCRETE_BRICK.get(), 4)
                .requires(ModItems.HEMP.get())
                .requires(ModItems.HEMP.get())
                .unlockedBy(getHasName(ModItems.HEMP.get()), has(ModItems.HEMP.get()))
                .save(pWriter);
    }



    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Cobalt.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
