package net.nicolas.calcium.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class DirtPlantBlock extends GenericPlantBlock {

    public DirtPlantBlock(Settings settings) {
        super(settings);
    }

    public static final MapCodec<DirtPlantBlock> CODEC = createCodec(DirtPlantBlock::new);
    @Override protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.offset(state.getModelOffset(pos));
    }

    @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos floorPos = pos.down();
        BlockState floor = world.getBlockState(floorPos);
        return floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
    }

}