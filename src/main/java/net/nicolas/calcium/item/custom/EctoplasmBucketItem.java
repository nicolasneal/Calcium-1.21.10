package net.nicolas.calcium.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.nicolas.calcium.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class EctoplasmBucketItem extends BucketItem {

    public EctoplasmBucketItem(Fluid fluid, Item.Settings settings) {
        super(fluid, settings);
    }

    @Override protected void playEmptyingSound(@Nullable LivingEntity user, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = ModSounds.ECTOPLASM_BUCKET_EMPTY;
        world.playSound(user, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(user, GameEvent.FLUID_PLACE, pos);
    }

}