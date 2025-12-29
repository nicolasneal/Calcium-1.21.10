package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.BrewingStandScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BrewingStandScreenHandler.class)
public class BrewingStandScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 0), index = 2)
    private int calcium$modifyPotionSlot0X(int x) {
        return 57;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 0), index = 3)
    private int calcium$modifyPotionSlot0Y(int y) {
        return 49;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 1), index = 2)
    private int calcium$modifyPotionSlot1X(int x) {
        return 80;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 1), index = 3)
    private int calcium$modifyPotionSlot1Y(int y) {
        return 56;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 2), index = 2)
    private int calcium$modifyPotionSlot2X(int x) {
        return 103;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 2), index = 3)
    private int calcium$modifyPotionSlot2Y(int y) {
        return 49;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$IngredientSlot;<init>(Lnet/minecraft/recipe/BrewingRecipeRegistry;Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyIngredientSlotX(int x) {
        return 80;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$IngredientSlot;<init>(Lnet/minecraft/recipe/BrewingRecipeRegistry;Lnet/minecraft/inventory/Inventory;III)V"), index = 4)
    private int calcium$modifyIngredientSlotY(int y) {
        return 15;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$FuelSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 2)
    private int calcium$modifyFuelSlotX(int x) {
        return 19;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$FuelSlot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyFuelSlotY(int y) {
        return 15;
    }

}