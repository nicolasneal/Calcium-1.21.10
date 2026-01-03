package net.nicolas.calcium.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.minecraft.item.Items.BOWL;

public class ModItems {

    public static final String MOD_ID = "calcium";

    // INGREDIENTS (MOB DROPS)

    public static final Item HIDE = register("hide", Item::new, new Item.Settings().maxCount(64));
    public static final Item FUR = register("fur", Item::new, new Item.Settings().maxCount(64));
    public static final Item PIXIE_DUST = register("pixie_dust", Item::new, new Item.Settings().maxCount(64));

    // INGREDIENTS (RESOURCES)

    public static final Item WOODEN_ROD = register("wooden_rod", Item::new, new Item.Settings().maxCount(64));
    public static final Item FLOUR = register("flour", Item::new, new Item.Settings().maxCount(64));
    public static final Item DOUGH = register("dough", Item::new, new Item.Settings().maxCount(64));
    public static final Item COOKIE_DOUGH = register("cookie_dough", Item::new, new Item.Settings().maxCount(64));
    public static final Item CAKE_BATTER = register("cake_batter", Item::new, new Item.Settings().maxCount(64));
    public static final Item PUMPKIN_SLICE = register("pumpkin_slice", Item::new, new Item.Settings().maxCount(64));

    // FOOD AND DRINK (MOB DROPS)

    public static final Item CHEVAL = register("cheval", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CHEVAL));
    public static final Item COOKED_CHEVAL = register("cooked_cheval", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CHEVAL));
    public static final Item BEAR = register("bear", Item::new, new Item.Settings().maxCount(64).food(ModFoods.BEAR));
    public static final Item COOKED_BEAR = register("cooked_bear", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_BEAR));
    public static final Item CAMEL = register("camel", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CAMEL));
    public static final Item COOKED_CAMEL = register("cooked_camel", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CAMEL));
    public static final Item CHEVON = register("chevon", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CHEVON));
    public static final Item COOKED_CHEVON = register("cooked_chevon", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CHEVON));
    public static final Item FROG = register("frog", Item::new, new Item.Settings().maxCount(64).food(ModFoods.FROG));
    public static final Item COOKED_FROG = register("cooked_frog", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_FROG));
    public static final Item TENTACLES = register("tentacles", Item::new, new Item.Settings().maxCount(64).food(ModFoods.TENTACLES));
    public static final Item COOKED_TENTACLES = register("cooked_tentacles", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_TENTACLES));
    public static final Item CHOCOLATE = register("chocolate", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CHOCOLATE));
    public static final Item WATER_BOWL = register("water_bowl", Item::new, new Item.Settings().maxCount(64).food(ModFoods.WATER_BOWL).component(DataComponentTypes.CONSUMABLE, ConsumableComponents.DRINK).useRemainder(BOWL).recipeRemainder(BOWL));

    private static <T extends Item> T register(String name, Function<Item.Settings, T> constructor, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
        T item = constructor.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemgroup) -> {
            itemgroup.add(WOODEN_ROD);
            itemgroup.add(HIDE);
            itemgroup.add(FUR);
            itemgroup.add(PIXIE_DUST);
            itemgroup.add(FLOUR);
            itemgroup.add(DOUGH);
            itemgroup.add(COOKIE_DOUGH);
            itemgroup.add(CAKE_BATTER);
            itemgroup.add(PUMPKIN_SLICE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(itemgroup -> {
            itemgroup.add(CHEVAL);
            itemgroup.add(COOKED_CHEVAL);
            itemgroup.add(BEAR);
            itemgroup.add(COOKED_BEAR);
            itemgroup.add(CAMEL);
            itemgroup.add(COOKED_CAMEL);
            itemgroup.add(CHEVON);
            itemgroup.add(COOKED_CHEVON);
            itemgroup.add(FROG);
            itemgroup.add(COOKED_FROG);
            itemgroup.add(TENTACLES);
            itemgroup.add(COOKED_TENTACLES);
            itemgroup.add(CHOCOLATE);
            itemgroup.add(WATER_BOWL);
        });

    }
}