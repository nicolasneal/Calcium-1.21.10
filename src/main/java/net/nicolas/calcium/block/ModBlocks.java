package net.nicolas.calcium.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final String MOD_ID = "calcium";

    // STONE VARIANT BLOCKS (#)

    public static final Block STONE_WALL = register("stone_wall", WallBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block POLISHED_STONE = register("polished_stone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block POLISHED_STONE_STAIRS = register("polished_stone_stairs", settings -> new StairsBlock(ModBlocks.POLISHED_STONE.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block POLISHED_STONE_SLAB = register("polished_stone_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block POLISHED_STONE_WALL = register("polished_stone_wall", WallBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_STONE_BRICK_STAIRS = register("cracked_stone_brick_stairs", settings -> new StairsBlock(Blocks.CRACKED_STONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_STONE_BRICK_SLAB = register("cracked_stone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block SMOOTH_STONE_STAIRS = register("smooth_stone_stairs", settings -> new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block ANDESITE_BRICKS = register("andesite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block ANDESITE_BRICK_STAIRS = register("andesite_brick_stairs", settings -> new StairsBlock(ModBlocks.ANDESITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block ANDESITE_BRICK_SLAB = register("andesite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_ANDESITE_BRICKS = register("cracked_andesite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_ANDESITE_BRICK_STAIRS = register("cracked_andesite_brick_stairs", settings -> new StairsBlock(ModBlocks.ANDESITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_ANDESITE_BRICK_SLAB = register("cracked_andesite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_ANDESITE = register("chiseled_andesite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DIORITE_BRICKS = register("diorite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DIORITE_BRICK_STAIRS = register("diorite_brick_stairs", settings -> new StairsBlock(ModBlocks.DIORITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DIORITE_BRICK_SLAB = register("diorite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DIORITE_BRICKS = register("cracked_diorite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DIORITE_BRICK_STAIRS = register("cracked_diorite_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_DIORITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DIORITE_BRICK_SLAB = register("cracked_diorite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_DIORITE = register("chiseled_diorite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block GRANITE_BRICKS = register("granite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block GRANITE_BRICK_STAIRS = register("granite_brick_stairs", settings -> new StairsBlock(ModBlocks.GRANITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block GRANITE_BRICK_SLAB = register("granite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_GRANITE_BRICKS = register("cracked_granite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_GRANITE_BRICK_STAIRS = register("cracked_granite_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_GRANITE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_GRANITE_BRICK_SLAB = register("cracked_granite_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_GRANITE = register("chiseled_granite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DEEPSLATE_STAIRS = register("deepslate_stairs", settings -> new StairsBlock(Blocks.DEEPSLATE.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block DEEPSLATE_SLAB = register("deepslate_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block CRACKED_DEEPSLATE_BRICK_STAIRS = register("cracked_deepslate_brick_stairs", settings -> new StairsBlock(Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block CRACKED_DEEPSLATE_BRICK_SLAB = register("cracked_deepslate_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block CRACKED_DEEPSLATE_TILE_STAIRS = register("cracked_deepslate_tile_stairs", settings -> new StairsBlock(Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_TILES).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block CRACKED_DEEPSLATE_TILE_SLAB = register("cracked_deepslate_tile_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_TILES).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 6.0F), true);
    public static final Block POLISHED_DRIPSTONE = register("polished_dripstone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.DRIPSTONE_BLOCK).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 1.0F), true);
    public static final Block DRIPSTONE_BRICKS = register("dripstone_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DRIPSTONE_BRICK_STAIRS = register("dripstone_brick_stairs", settings -> new StairsBlock(ModBlocks.DRIPSTONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DRIPSTONE_BRICK_SLAB = register("dripstone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DRIPSTONE_BRICKS = register("cracked_dripstone_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DRIPSTONE_BRICK_STAIRS = register("cracked_dripstone_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_DRIPSTONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CRACKED_DRIPSTONE_BRICK_SLAB = register("cracked_dripstone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_DRIPSTONE = register("chiseled_dripstone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.DRIPSTONE_BLOCK).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 1.0F), true);
    public static final Block SANDSTONE_BRICK_STAIRS = register("sandstone_brick_stairs", settings -> new StairsBlock(Blocks.CUT_SANDSTONE.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_SANDSTONE_BRICKS = register("cracked_sandstone_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_SANDSTONE_BRICK_STAIRS = register("cracked_sandstone_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_SANDSTONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_SANDSTONE_BRICK_SLAB = register("cracked_sandstone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block RED_SANDSTONE_BRICK_STAIRS = register("red_sandstone_brick_stairs", settings -> new StairsBlock(Blocks.CUT_RED_SANDSTONE.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_RED_SANDSTONE_BRICKS = register("cracked_red_sandstone_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_RED_SANDSTONE_BRICK_STAIRS = register("cracked_red_sandstone_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_RED_SANDSTONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_RED_SANDSTONE_BRICK_SLAB = register("cracked_red_sandstone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CRACKED_BLACKSTONE_BRICK_STAIRS = register("cracked_blackstone_brick_stairs", settings -> new StairsBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block CRACKED_BLACKSTONE_BRICK_SLAB = register("cracked_blackstone_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block BASALT_STAIRS = register("basalt_stairs", settings -> new StairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block BASALT_SLAB = register("basalt_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block POLISHED_BASALT = register("polished_basalt", Block::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block POLISHED_BASALT_STAIRS = register("polished_basalt_stairs", settings -> new StairsBlock(ModBlocks.POLISHED_BASALT.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block POLISHED_BASALT_SLAB = register("polished_basalt_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block BASALT_BRICKS = register("basalt_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block BASALT_BRICK_STAIRS = register("basalt_brick_stairs", settings -> new StairsBlock(ModBlocks.BASALT_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block BASALT_BRICK_SLAB = register("basalt_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block CRACKED_BASALT_BRICKS = register("cracked_basalt_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block CRACKED_BASALT_BRICK_STAIRS = register("cracked_basalt_brick_stairs", settings -> new StairsBlock(ModBlocks.CRACKED_BASALT_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block CRACKED_BASALT_BRICK_SLAB = register("cracked_basalt_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block CHISELED_BASALT = register("chiseled_basalt", Block::new, Block.Settings.create().sounds(BlockSoundGroup.BASALT).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F), true);
    public static final Block QUARTZ_BRICK_STAIRS = register("quartz_brick_stairs", settings -> new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), settings), Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block QUARTZ_BRICK_SLAB = register("quartz_brick_slab", SlabBlock::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(0.8F, 0.8F), true);
    public static final Block CHISELED_RED_NETHER_BRICKS = register("chiseled_red_nether_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.NETHER_BRICKS).mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F), true);

    // PLANT BLOCKS (8)

    public static final Block WILD_WHEAT = register("wild_wheat", ShortPlantBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).burnable().pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block WILD_CARROT = register("wild_carrot", ShortPlantBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).burnable().pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block WILD_POTATO = register("wild_potato", ShortPlantBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).burnable().pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block WILD_BEETROOT = register("wild_beetroot", ShortPlantBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).burnable().pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block PONTEDERIA = register("pontederia", (settings) -> new FlowerBlock(StatusEffects.WATER_BREATHING, 5.0f, settings), Block.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).burnable().pistonBehavior(PistonBehavior.DESTROY),true);
    public static final Block BUSY_LIZZIE = register("busy_lizzie", BushBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block GOLDENROD = register("goldenrod", TallFlowerBlock::new, Block.Settings.create().mapColor(MapColor.YELLOW).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block PAMPAS = register("pampas", TallFlowerBlock::new, Block.Settings.create().mapColor(MapColor.PALE_YELLOW).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block CLOVERS = register("clovers", FlowerbedBlock::new, Block.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.FLOWERBED).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block POTTED_PONTEDERIA = register("potted_pontederia", settings -> new FlowerPotBlock(PONTEDERIA, settings), Block.Settings.copy(Blocks.FLOWER_POT), false);

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((itemgroup) -> {
            itemgroup.add(STONE_WALL);
            itemgroup.add(POLISHED_STONE);
            itemgroup.add(POLISHED_STONE_STAIRS);
            itemgroup.add(POLISHED_STONE_SLAB);
            itemgroup.add(POLISHED_STONE_WALL);
            itemgroup.add(CRACKED_STONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_STONE_BRICK_SLAB);
            itemgroup.add(SMOOTH_STONE_STAIRS);
            itemgroup.add(ANDESITE_BRICKS);
            itemgroup.add(ANDESITE_BRICK_STAIRS);
            itemgroup.add(ANDESITE_BRICK_SLAB);
            itemgroup.add(CRACKED_ANDESITE_BRICKS);
            itemgroup.add(CRACKED_ANDESITE_BRICK_STAIRS);
            itemgroup.add(CRACKED_ANDESITE_BRICK_SLAB);
            itemgroup.add(CHISELED_ANDESITE);
            itemgroup.add(DIORITE_BRICKS);
            itemgroup.add(DIORITE_BRICK_STAIRS);
            itemgroup.add(DIORITE_BRICK_SLAB);
            itemgroup.add(CRACKED_DIORITE_BRICKS);
            itemgroup.add(CRACKED_DIORITE_BRICK_STAIRS);
            itemgroup.add(CRACKED_DIORITE_BRICK_SLAB);
            itemgroup.add(CHISELED_DIORITE);
            itemgroup.add(GRANITE_BRICKS);
            itemgroup.add(GRANITE_BRICK_STAIRS);
            itemgroup.add(GRANITE_BRICK_SLAB);
            itemgroup.add(CRACKED_GRANITE_BRICKS);
            itemgroup.add(CRACKED_GRANITE_BRICK_STAIRS);
            itemgroup.add(CRACKED_GRANITE_BRICK_SLAB);
            itemgroup.add(CHISELED_GRANITE);
            itemgroup.add(DEEPSLATE_STAIRS);
            itemgroup.add(DEEPSLATE_SLAB);
            itemgroup.add(CRACKED_DEEPSLATE_BRICK_STAIRS);
            itemgroup.add(CRACKED_DEEPSLATE_BRICK_SLAB);
            itemgroup.add(CRACKED_DEEPSLATE_TILE_STAIRS);
            itemgroup.add(CRACKED_DEEPSLATE_TILE_SLAB);
            itemgroup.add(POLISHED_DRIPSTONE);
            itemgroup.add(DRIPSTONE_BRICKS);
            itemgroup.add(DRIPSTONE_BRICK_STAIRS);
            itemgroup.add(DRIPSTONE_BRICK_SLAB);
            itemgroup.add(CRACKED_DRIPSTONE_BRICKS);
            itemgroup.add(CRACKED_DRIPSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_DRIPSTONE_BRICK_SLAB);
            itemgroup.add(CHISELED_DRIPSTONE);
            itemgroup.add(SANDSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_SANDSTONE_BRICKS);
            itemgroup.add(CRACKED_SANDSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_SANDSTONE_BRICK_SLAB);
            itemgroup.add(RED_SANDSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_RED_SANDSTONE_BRICKS);
            itemgroup.add(CRACKED_RED_SANDSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_RED_SANDSTONE_BRICK_SLAB);
            itemgroup.add(BASALT_STAIRS);
            itemgroup.add(BASALT_SLAB);
            itemgroup.add(POLISHED_BASALT);
            itemgroup.add(POLISHED_BASALT_STAIRS);
            itemgroup.add(POLISHED_BASALT_SLAB);
            itemgroup.add(CRACKED_BLACKSTONE_BRICK_STAIRS);
            itemgroup.add(CRACKED_BLACKSTONE_BRICK_SLAB);
            itemgroup.add(BASALT_BRICKS);
            itemgroup.add(BASALT_BRICK_STAIRS);
            itemgroup.add(BASALT_BRICK_SLAB);
            itemgroup.add(CRACKED_BASALT_BRICKS);
            itemgroup.add(CRACKED_BASALT_BRICK_STAIRS);
            itemgroup.add(CRACKED_BASALT_BRICK_SLAB);
            itemgroup.add(CHISELED_BASALT);
            itemgroup.add(QUARTZ_BRICK_STAIRS);
            itemgroup.add(QUARTZ_BRICK_SLAB);
            itemgroup.add(CHISELED_RED_NETHER_BRICKS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((itemgroup) -> {
            itemgroup.add(WILD_WHEAT);
            itemgroup.add(WILD_CARROT);
            itemgroup.add(WILD_POTATO);
            itemgroup.add(WILD_BEETROOT);
            itemgroup.add(PONTEDERIA);
            itemgroup.add(BUSY_LIZZIE);
            itemgroup.add(GOLDENROD);
            itemgroup.add(PAMPAS);
            itemgroup.add(CLOVERS);
        });

    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean registerItem) {

        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (registerItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
    }

}