package net.nicolas.calcium.mixin.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AbstractCraftingScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin extends AbstractCraftingScreenHandler {

    public CraftingScreenHandlerMixin(ScreenHandlerType<?> type, int syncId, int width, int height) {
        super(type, syncId, width, height);
    }

    @Redirect(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CraftingScreenHandler;addInputSlots(II)V"))
    private void calcium$redirectAddInputSlots(CraftingScreenHandler instance, int x, int y) {
        super.addInputSlots(31, 17);
    }

    @Redirect(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CraftingScreenHandler;addResultSlot(Lnet/minecraft/entity/player/PlayerEntity;II)Lnet/minecraft/screen/slot/Slot;"))
    private Slot calcium$redirectAddResultSlot(CraftingScreenHandler instance, PlayerEntity player, int x, int y) {
        return super.addResultSlot(player, 125, 35);
    }

}