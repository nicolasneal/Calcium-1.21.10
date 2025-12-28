package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.AbstractFurnaceScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractFurnaceScreenHandler.class)
public class AbstractFurnaceScreenHandlerMixin {

    @ModifyArg(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/recipe/book/RecipeBookType;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 2)
    private int calcium$modifyInputSlotX(int x) {
        return 48;
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/recipe/book/RecipeBookType;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/FurnaceFuelSlot;<init>(Lnet/minecraft/screen/AbstractFurnaceScreenHandler;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyFuelSlotX(int x) {
        return 48;
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/recipe/book/RecipeBookType;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/FurnaceOutputSlot;<init>(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyOutputSlotX(int x) {
        return 109;
    }

}