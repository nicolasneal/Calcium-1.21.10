package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.CartographyTableScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CartographyTableScreenHandler.class)
public class CartographyTableScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CartographyTableScreenHandler$3;<init>(Lnet/minecraft/screen/CartographyTableScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyMapSlotX(int x) {
        return 12;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CartographyTableScreenHandler$4;<init>(Lnet/minecraft/screen/CartographyTableScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyMaterialSlotX(int x) {
        return 12;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CartographyTableScreenHandler$4;<init>(Lnet/minecraft/screen/CartographyTableScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyMaterialSlotY(int y) {
        return 55;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CartographyTableScreenHandler$5;<init>(Lnet/minecraft/screen/CartographyTableScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 3)
    private int calcium$modifyResultSlotX(int x) {
        return 144;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/CartographyTableScreenHandler$5;<init>(Lnet/minecraft/screen/CartographyTableScreenHandler;Lnet/minecraft/inventory/Inventory;IIILnet/minecraft/screen/ScreenHandlerContext;)V"), index = 4)
    private int calcium$modifyResultSlotY(int y) {
        return 35;
    }

}