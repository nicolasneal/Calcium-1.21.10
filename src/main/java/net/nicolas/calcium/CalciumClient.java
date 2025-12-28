package net.nicolas.calcium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.world.biome.GrassColors;
import net.nicolas.calcium.block.ModBlocks;
import net.nicolas.calcium.screen.CustomBeaconScreen;
import net.nicolas.calcium.screen.EnchantingScreen;

public class CalciumClient implements ClientModInitializer {

    @Override public void onInitializeClient() {

        HandledScreens.register(Calcium.ENCHANTING_SCREEN_HANDLER, EnchantingScreen::new);
        HandledScreens.register(Calcium.CUSTOM_BEACON_SCREEN_HANDLER, CustomBeaconScreen::new);

        BlockRenderLayerMap.putBlock(ModBlocks.IRON_GRATE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_GRATE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_CHAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WILD_WHEAT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WILD_CARROT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WILD_POTATO, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WILD_BEETROOT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PONTEDERIA, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BUSY_LIZZIE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLDENROD, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BARLEY, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SEA_OATS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PAMPAS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ICY_IRIS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TALL_ICY_IRIS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CLOVERS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_PONTEDERIA, BlockRenderLayer.CUTOUT);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                return BiomeColors.getGrassColor(world, pos);
            }
            return GrassColors.getColor(0.5, 1.0);
        },
            ModBlocks.WILD_CARROT,
            ModBlocks.WILD_POTATO,
            ModBlocks.WILD_BEETROOT,
            ModBlocks.BARLEY,
            ModBlocks.CLOVERS
        );

    }
}