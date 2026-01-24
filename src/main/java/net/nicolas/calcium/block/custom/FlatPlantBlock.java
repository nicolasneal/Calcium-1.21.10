package net.nicolas.calcium.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class FlatPlantBlock extends GenericPlantBlock {

    public static final MapCodec<FlatPlantBlock> CODEC = createCodec(FlatPlantBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0);
    public static final TagKey<Block> VALID_BASES = TagKey.of(RegistryKeys.BLOCK, Identifier.of("calcium", "ember_sprouts_placement"));

    public FlatPlantBlock(Settings settings) {
        super(settings);
    }

    @Override protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.offset(state.getModelOffset(pos));
    }

    @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos floorPos = pos.down();
        BlockState floorState = world.getBlockState(floorPos);
        return floorState.isIn(VALID_BASES);
    }

}