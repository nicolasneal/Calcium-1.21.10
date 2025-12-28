package net.nicolas.calcium.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Shadow
    protected static BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        throw new AssertionError();
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUseBowl(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if ((Object) this != Items.BOWL) {
            return;
        }

        ItemStack itemStack = user.getStackInHand(hand);

        BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return;
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            var blockPos = hitResult.getBlockPos();

            if (!world.canEntityModifyAt(user, blockPos)) {
                return;
            }

            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);

                Item waterBowl = Registries.ITEM.get(Identifier.of("calcium", "water_bowl"));
                ItemStack filledStack = ItemUsage.exchangeStack(itemStack, user, new ItemStack(waterBowl));

                user.incrementStat(Stats.USED.getOrCreateStat((Item) (Object) this));
                cir.setReturnValue(ActionResult.SUCCESS.withNewHandStack(filledStack));
            }

        }
    }
}