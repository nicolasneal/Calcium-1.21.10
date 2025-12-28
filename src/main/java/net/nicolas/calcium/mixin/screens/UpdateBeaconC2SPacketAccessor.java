package net.nicolas.calcium.mixin.screens;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.network.packet.c2s.play.UpdateBeaconC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Optional;

@Mixin(UpdateBeaconC2SPacket.class)
public interface UpdateBeaconC2SPacketAccessor {

    @Accessor("primary")
    Optional<RegistryEntry<StatusEffect>> getPrimary();

    @Accessor("secondary")
    Optional<RegistryEntry<StatusEffect>> getSecondary();

}