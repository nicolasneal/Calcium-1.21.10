package net.nicolas.calcium.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkeletonEntity.class)
public class WitherSkeletonEntityMixin {

    @Inject(method = "initEquipment", at = @At("TAIL"))
    private void equipGoldSword(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        WitherSkeletonEntity self = (WitherSkeletonEntity) (Object) this;
        self.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
    }

}