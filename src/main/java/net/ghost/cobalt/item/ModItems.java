package net.ghost.cobalt.item;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cobalt.MOD_ID);

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_CHUNK = ITEMS.register("iron_chunk",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEMPCRETE_BRICK = ITEMS.register("hempcrete_brick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEMP = ITEMS.register("hemp",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PEANUT = ITEMS.register("peanut",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEMP_SEEDS = ITEMS.register("hemp_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HEMP_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEANUT_SEEDS = ITEMS.register("peanut_seeds",
            () -> new ItemNameBlockItem(ModBlocks.PEANUT_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
