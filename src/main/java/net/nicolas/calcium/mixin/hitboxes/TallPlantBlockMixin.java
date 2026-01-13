package net.nicolas.calcium.mixin.hitboxes;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallPlantBlock.class)
public abstract class TallPlantBlockMixin extends PlantBlock {

    public TallPlantBlockMixin(Settings settings) {
        super(settings);
    }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createColumnShape(12.0, 0.0, 16.0).offset(state.getModelOffset(pos));
    }

}