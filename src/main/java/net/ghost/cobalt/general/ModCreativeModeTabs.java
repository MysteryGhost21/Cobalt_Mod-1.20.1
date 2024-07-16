package net.ghost.cobalt.general;

import net.ghost.cobalt.Cobalt;
import net.ghost.cobalt.block.ModBlocks;
import net.ghost.cobalt.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cobalt.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COBALT_TAB = CREATIVE_MODE_TABS.register("cobalt_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COBALT_INGOT.get()))
                    .title(Component.translatable("creativetab.cobalt_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //Items
                        pOutput.accept(ModItems.RAW_COBALT.get());
                        pOutput.accept(ModItems.COBALT_INGOT.get());
                        pOutput.accept(ModItems.HEMPCRETE_BRICK.get());
                        pOutput.accept(ModItems.HEMP.get());
                        pOutput.accept(ModItems.PEANUT.get());
                        pOutput.accept(ModItems.PEANUT_SEEDS.get());
                        pOutput.accept(ModItems.HEMP_SEEDS.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        //Blocks
                        pOutput.accept(ModBlocks.COBALT_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_COBALT_ORE.get());
                        pOutput.accept(ModBlocks.COBALT_BLOCK.get());
                        pOutput.accept(ModBlocks.HEMPCRETE_BRICKS.get());
                        pOutput.accept(ModBlocks.GEM_POLISHING_STATION.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
