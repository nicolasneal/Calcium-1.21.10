package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LoomScreenHandler.class)
public class LoomScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$3;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyBannerSlotX(int x) {
        return 12;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$3;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyBannerSlotY(int y) {
        return 25;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$4;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyDyeSlotX(int x) {
        return 32;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$4;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyDyeSlotY(int y) {
        return 25;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$5;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyPatternSlotX(int x) {
        return 22;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler$6;<init>(Lnet/minecraft/screen/LoomScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 3)
    private int calcium$modifyOutputSlotX(int x) {
        return 144;
    }

}