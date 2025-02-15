package net.ghost.cobalt.block;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.custom.GemPolishingStationBlock;
import net.ghost.cobalt.block.custom.HempCropBlock;
import net.ghost.cobalt.block.custom.OreExtractorMKOneBlock;
import net.ghost.cobalt.block.custom.PeanutCropBlock;
import net.ghost.cobalt.item.ModItems;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Cobalt.MOD_ID);

    public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> COBALT_BLOCK = registerBlock("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> CONCENTRATED_IRON_ORE = registerBlock("concentrated_iron_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noLootTable()));

    public static final RegistryObject<Block> HEMPCRETE_BRICKS = registerBlock("hempcrete_bricks",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PEANUT_CROP = BLOCKS.register("peanut_crop",
            () -> new PeanutCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> HEMP_CROP = BLOCKS.register("hemp_crop",
            () -> new HempCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> GEM_POLISHING_STATION = registerBlock("gem_polishing_station",
            () -> new GemPolishingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> ORE_EXTRACTOR_MKONE = registerBlock("ore_extractor_mkone",
            () -> new OreExtractorMKOneBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(s -> 2)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
