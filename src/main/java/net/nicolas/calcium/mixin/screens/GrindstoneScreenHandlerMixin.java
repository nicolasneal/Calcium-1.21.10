package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$2;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyInput1X(int x) {
        return 48;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$2;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyInput1Y(int y) {
        return 24;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$3;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyInput2X(int x) {
        return 48;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$3;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyInput2Y(int y) {
        return 46;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$4;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 3)
    private int calcium$modifyResultX(int x) {
        return 107;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler$4;<init>(Lnet/minecraft/screen/GrindstoneScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 4)
    private int calcium$modifyResultY(int y) {
        return 35;
    }

}