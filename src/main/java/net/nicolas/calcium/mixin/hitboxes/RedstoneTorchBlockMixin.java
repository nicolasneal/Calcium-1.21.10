package net.nicolas.calcium.mixin.hitboxes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RedstoneTorchBlock.class)
public abstract class RedstoneTorchBlockMixin extends Block {

    public RedstoneTorchBlockMixin(Settings settings) {
        super(settings);
    }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createColumnShape(4.0, 0.0, 11.0);
    }

}