package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.StonecutterScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StonecutterScreenHandler.class)
public class StonecutterScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 2)
    private int calcium$modifyInputSlotX(int x) {
        return 17;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyInputSlotY(int y) {
        return 35;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/StonecutterScreenHandler$2;<init>(Lnet/minecraft/screen/StonecutterScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 4)
    private int calcium$modifyOutputSlotY(int y) {
        return 35;
    }

}