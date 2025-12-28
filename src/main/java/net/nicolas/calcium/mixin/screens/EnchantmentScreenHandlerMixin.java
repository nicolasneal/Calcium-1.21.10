package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler$2;<init>(Lnet/minecraft/screen/EnchantmentScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyItemSlotX(int x) {
        return 12;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler$2;<init>(Lnet/minecraft/screen/EnchantmentScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyItemSlotY(int y) {
        return 49;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler$3;<init>(Lnet/minecraft/screen/EnchantmentScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyLapisSlotX(int x) {
        return 34;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler$3;<init>(Lnet/minecraft/screen/EnchantmentScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyLapisSlotY(int y) {
        return 49;
    }

}