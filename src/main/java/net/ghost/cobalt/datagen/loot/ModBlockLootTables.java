package net.ghost.cobalt.datagen.loot;

import net.ghost.cobalt.block.ModBlocks;
import net.ghost.cobalt.block.custom.PeanutCropBlock;
import net.ghost.cobalt.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.COBALT_BLOCK.get());
        this.dropSelf(ModBlocks.HEMPCRETE_BRICKS.get());
        this.dropSelf(ModBlocks.GEM_POLISHING_STATION.get());
        this.dropSelf(ModBlocks.ORE_EXTRACTOR_MKONE.get());

        this.add(ModBlocks.COBALT_ORE.get(),
                block -> createIronLikeOreDrops(ModBlocks.COBALT_ORE.get(), ModItems.RAW_COBALT.get()));
        this.add(ModBlocks.DEEPSLATE_COBALT_ORE.get(),
                block -> createIronLikeOreDrops(ModBlocks.DEEPSLATE_COBALT_ORE.get(), ModItems.RAW_COBALT.get()));

        LootItemCondition.Builder peanut_builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.PEANUT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PeanutCropBlock.AGE, 4));
        this.add(ModBlocks.PEANUT_CROP.get(), createCropDrops(ModBlocks.PEANUT_CROP.get(), ModItems.PEANUT.get(),
                ModItems.PEANUT_SEEDS.get(), peanut_builder));

        LootItemCondition.Builder hemp_builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.HEMP_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PeanutCropBlock.AGE, 4));
        this.add(ModBlocks.HEMP_CROP.get(), createCropDrops(ModBlocks.HEMP_CROP.get(), ModItems.HEMP.get(),
                ModItems.HEMP_SEEDS.get(), hemp_builder));



    }

    protected LootTable.Builder createIronLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 1F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
