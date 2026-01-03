package net.nicolas.calcium.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class TallDryPlantBlock extends TallPlantBlock {
    public static final MapCodec<TallDryPlantBlock> CODEC = createCodec(TallDryPlantBlock::new);

    public TallDryPlantBlock(Settings settings) {
        super(settings);
    }

    @Override public MapCodec<TallDryPlantBlock> getCodec() {
        return CODEC;
    }

    @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DRY_VEGETATION_MAY_PLACE_ON) || super.canPlantOnTop(floor, world, pos);
    }

}