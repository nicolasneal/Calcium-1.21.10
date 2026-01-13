package net.nicolas.calcium.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record EnchantingRecipeInput(ItemStack tablet, List<ItemStack> ingredients) implements RecipeInput {

    @Override public ItemStack getStackInSlot(int slot) {
        if (slot == 0) return tablet;
        if (slot - 1 < ingredients.size()) return ingredients.get(slot - 1);
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return tablet.isEmpty() && ingredients.isEmpty();
    }

}