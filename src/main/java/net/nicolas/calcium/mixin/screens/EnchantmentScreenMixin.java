package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(EnchantmentScreen.class)
public abstract class EnchantmentScreenMixin extends HandledScreen<EnchantmentScreenHandler> {

    public EnchantmentScreenMixin(EnchantmentScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // Book Entity Repositioning

    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/EnchantmentScreen;drawBook(Lnet/minecraft/client/gui/DrawContext;II)V"))
    private void calcium$modifyBookPosition(Args args) {
        args.set(1, this.x - 1);
        args.set(2, this.y + 1);
    }

    // Added Tooltips

    @Inject(method = "render", at = @At("TAIL"))
    private void calcium$renderItemHint(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.focusedSlot != null && this.focusedSlot.id == 0 && !this.focusedSlot.hasStack()) {
            context.drawTooltip(this.textRenderer, Text.literal("Add Enchantable Item"), mouseX, mouseY);
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void calcium$renderLapisHint(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.focusedSlot != null && this.focusedSlot.id == 1 && !this.focusedSlot.hasStack()) {
            context.drawTooltip(this.textRenderer, Text.literal("Add ").append(Text.translatable(Items.LAPIS_LAZULI.getTranslationKey())), mouseX, mouseY);
        }
    }

}