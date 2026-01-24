package net.nicolas.calcium.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnchantingRecipe implements Recipe<EnchantingRecipeInput> {

    private final DefaultedList<Ingredient> ingredients;
    private final RegistryEntry<Enchantment> resultEnchantment;
    private final int resultLevel;
    private final Optional<RegistryEntry<Enchantment>> requiredEnchantment;
    private final int requiredLevel;

    public EnchantingRecipe(DefaultedList<Ingredient> ingredients, RegistryEntry<Enchantment> resultEnchantment, int resultLevel, Optional<RegistryEntry<Enchantment>> requiredEnchantment, int requiredLevel) {
        this.ingredients = ingredients;
        this.resultEnchantment = resultEnchantment;
        this.resultLevel = resultLevel;
        this.requiredEnchantment = requiredEnchantment;
        this.requiredLevel = requiredLevel;
    }

    @Override public boolean matches(EnchantingRecipeInput input, World world) {

        List<ItemStack> inputsToCheck = new ArrayList<>(input.ingredients());
        inputsToCheck.removeIf(ItemStack::isEmpty);

        if (inputsToCheck.size() != this.ingredients.size()) {
            return false;
        }

        for (Ingredient ingredient : this.ingredients) {
            boolean found = false;
            for (int i = 0; i < inputsToCheck.size(); i++) {
                if (ingredient.test(inputsToCheck.get(i))) {
                    inputsToCheck.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        if (requiredEnchantment.isPresent()) {
            ItemStack tablet = input.tablet();
            var componentType = tablet.isOf(net.minecraft.item.Items.ENCHANTED_BOOK)
                ? DataComponentTypes.STORED_ENCHANTMENTS
                : DataComponentTypes.ENCHANTMENTS;
            ItemEnchantmentsComponent enchantments = tablet.get(componentType);
            if (enchantments == null) return false;
            int currentLevel = enchantments.getLevel(requiredEnchantment.get());
            if (currentLevel < requiredLevel) return false;
        }

        ItemStack tablet = input.tablet();
        Enchantment enchantment = this.resultEnchantment.value();

        boolean isBook = tablet.isOf(net.minecraft.item.Items.BOOK) || tablet.isOf(net.minecraft.item.Items.ENCHANTED_BOOK);
        if (!isBook && !enchantment.isSupportedItem(tablet)) {
            return false;
        }

        var componentType = tablet.isOf(net.minecraft.item.Items.ENCHANTED_BOOK)
            ? DataComponentTypes.STORED_ENCHANTMENTS
            : DataComponentTypes.ENCHANTMENTS;

        ItemEnchantmentsComponent existingEnchants = tablet.get(componentType);

        int currentLevel = existingEnchants != null ? existingEnchants.getLevel(this.resultEnchantment) : 0;
        if (currentLevel != this.resultLevel - 1) {
            return false;
        }

        if (existingEnchants != null) {
            for (RegistryEntry<Enchantment> entry : existingEnchants.getEnchantments()) {
                if (entry.equals(this.resultEnchantment)) continue;
                if (!Enchantment.canBeCombined(entry, this.resultEnchantment)) {
                    return false;
                }
            }
        }

        return true;

    }

    @Override public ItemStack craft(EnchantingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {

        ItemStack inputStack = input.tablet();
        ItemStack result;

        if (inputStack.isOf(net.minecraft.item.Items.BOOK)) {
            result = new ItemStack(net.minecraft.item.Items.ENCHANTED_BOOK, inputStack.getCount());
            if (inputStack.contains(DataComponentTypes.CUSTOM_NAME)) {
                result.set(DataComponentTypes.CUSTOM_NAME, inputStack.get(DataComponentTypes.CUSTOM_NAME));
            }
            if (inputStack.contains(DataComponentTypes.LORE)) {
                result.set(DataComponentTypes.LORE, inputStack.get(DataComponentTypes.LORE));
            }
        }
        else {
            result = inputStack.copy();
        }

        var componentType = result.isOf(net.minecraft.item.Items.ENCHANTED_BOOK)
            ? DataComponentTypes.STORED_ENCHANTMENTS
            : DataComponentTypes.ENCHANTMENTS;

        ItemEnchantmentsComponent existingEnchantments = result.get(componentType);

        if (existingEnchantments == null) {
            existingEnchantments = ItemEnchantmentsComponent.DEFAULT;
        }

        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(existingEnchantments);
        builder.set(resultEnchantment, resultLevel);
        result.set(componentType, builder.build());

        return result;
        
    }

    @Override public RecipeSerializer<? extends Recipe<EnchantingRecipeInput>> getSerializer() {
        return ModRecipes.ENCHANTING_SERIALIZER;
    }

    @Override public RecipeType<? extends Recipe<EnchantingRecipeInput>> getType() {
        return ModRecipes.ENCHANTING_TYPE;
    }

    @Override public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forShapeless(this.ingredients);
    }

    @Override public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<EnchantingRecipe> {

        public static final MapCodec<EnchantingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(r -> r.ingredients),
            Enchantment.ENTRY_CODEC.fieldOf("result_enchantment").forGetter(r -> r.resultEnchantment),
            Codec.INT.fieldOf("result_level").forGetter(r -> r.resultLevel),
            Enchantment.ENTRY_CODEC.optionalFieldOf("required_enchantment").forGetter(r -> r.requiredEnchantment),
            Codec.INT.optionalFieldOf("required_level", 0).forGetter(r -> r.requiredLevel)
        ).apply(instance, (ingredients, resEnch, resLvl, reqEnch, reqLvl) -> {
            DefaultedList<Ingredient> list = DefaultedList.of();
            list.addAll(ingredients);
            return new EnchantingRecipe(list, resEnch, resLvl, reqEnch, reqLvl);
        }));

        public static final PacketCodec<RegistryByteBuf, EnchantingRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                EnchantingRecipe.Serializer::write, EnchantingRecipe.Serializer::read
        );

        @Override public MapCodec<EnchantingRecipe> codec() {
            return CODEC;
        }

        @Override public PacketCodec<RegistryByteBuf, EnchantingRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static EnchantingRecipe read(RegistryByteBuf buf) {

            int size = buf.readInt();
            DefaultedList<Ingredient> ingredients = DefaultedList.of();

            for(int i = 0; i < size; i++) {
                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
            }

            RegistryEntry<Enchantment> resEnch = Enchantment.ENTRY_PACKET_CODEC.decode(buf);
            int resLvl = buf.readInt();

            Optional<RegistryEntry<Enchantment>> reqEnch = Optional.empty();
            if (buf.readBoolean()) {
                reqEnch = Optional.of(Enchantment.ENTRY_PACKET_CODEC.decode(buf));
            }
            int reqLvl = buf.readInt();

            return new EnchantingRecipe(ingredients, resEnch, resLvl, reqEnch, reqLvl);

        }

        private static void write(RegistryByteBuf buf, EnchantingRecipe recipe) {
            buf.writeInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            Enchantment.ENTRY_PACKET_CODEC.encode(buf, recipe.resultEnchantment);
            buf.writeInt(recipe.resultLevel);
            buf.writeBoolean(recipe.requiredEnchantment.isPresent());
            recipe.requiredEnchantment.ifPresent(e -> Enchantment.ENTRY_PACKET_CODEC.encode(buf, e));
            buf.writeInt(recipe.requiredLevel);
        }

    }

}