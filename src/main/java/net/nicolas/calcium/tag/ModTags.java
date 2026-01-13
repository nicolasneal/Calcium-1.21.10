package net.nicolas.calcium.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> ENCHANTABLE = TagKey.of(RegistryKeys.ITEM, Identifier.of("calcium", "enchantable"));
}