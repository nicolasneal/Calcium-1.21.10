package net.nicolas.calcium.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract boolean isOf(Item item);
    @Shadow public abstract boolean hasEnchantments();

    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void calcium$fixChainmailRarity(CallbackInfoReturnable<Rarity> cir) {

        if (this.isOf(Items.CHAINMAIL_HELMET) || this.isOf(Items.CHAINMAIL_CHESTPLATE) || this.isOf(Items.CHAINMAIL_LEGGINGS) || this.isOf(Items.CHAINMAIL_BOOTS)) {
            if (this.hasEnchantments()) {
                cir.setReturnValue(Rarity.RARE);
            } else {
                cir.setReturnValue(Rarity.COMMON);
            }
        }

    }

}