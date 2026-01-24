package net.nicolas.calcium.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ECTOPLASM_BUCKET_EMPTY = register("item.bucket.empty_ectoplasm");
    public static final SoundEvent ECTOPLASM_BUCKET_FILL = register("item.bucket.fill_ectoplasm");
    public static final SoundEvent ECTOPLASM_AMBIENT = register("block.ectoplasm.whispering");

    public static final SoundEvent SOUL_SLATE_BREAK = register("block.soulslate.break");
    public static final SoundEvent SOUL_SLATE_PLACE = register("block.soulslate.place");
    public static final SoundEvent SOUL_SLATE_STEP = register("block.soulslate.step");

    public static final BlockSoundGroup SOULSLATE = new BlockSoundGroup(1.0F, 1.0F, SOUL_SLATE_BREAK, SOUL_SLATE_STEP, SOUL_SLATE_PLACE, SOUL_SLATE_STEP, SOUL_SLATE_STEP);

    private static SoundEvent register(String name) {
        Identifier id = Identifier.of("calcium", name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initialize() {}

}