package net.nicolas.calcium.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GlowstoneOreBlock extends RedstoneOreBlock {

    private static final DustParticleEffect GLOW_DUST = new DustParticleEffect(0xFFDB72, 1.0F);

    public GlowstoneOreBlock(Settings settings) {
        super(settings);
    }

    @Override public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        light(state, world, pos);
    }

    @Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects()) {
            light(state, world, pos);
        }
    }

    @Override protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            spawnGlowParticles(world, pos);
        } else {
            light(state, world, pos);
        }
        return stack.getItem() instanceof BlockItem && new ItemPlacementContext(player, hand, stack, hit).canPlace() ? ActionResult.PASS : ActionResult.SUCCESS;
    }

    @Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            spawnGlowParticles(world, pos);
        }
    }

    private static void light(BlockState state, World world, BlockPos pos) {
        spawnGlowParticles(world, pos);
        if (!state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, true), 3);
        }
    }

    private static void spawnGlowParticles(World world, BlockPos pos) {
        if (world.isClient()) {
            Random random = world.random;
            for (Direction direction : Direction.values()) {
                BlockPos blockPos = pos.offset(direction);
                if (!world.getBlockState(blockPos).isOpaqueFullCube()) {
                    Direction.Axis axis = direction.getAxis();
                    double e = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double) direction.getOffsetX() : (double) random.nextFloat();
                    double f = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double) direction.getOffsetY() : (double) random.nextFloat();
                    double g = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double) direction.getOffsetZ() : (double) random.nextFloat();
                    world.addParticleClient(GLOW_DUST, (double) pos.getX() + e, (double) pos.getY() + f, (double) pos.getZ() + g, 0.0, 0.0, 0.0);
                }
            }
        }
    }

}