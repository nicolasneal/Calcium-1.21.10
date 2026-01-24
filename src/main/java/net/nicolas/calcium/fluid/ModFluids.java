package net.nicolas.calcium.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {

    public static final FlowableFluid ECTOPLASM_STILL = register("ectoplasm_still", new EctoplasmFluid.Still());
    public static final FlowableFluid ECTOPLASM_FLOWING = register("ectoplasm_flowing", new EctoplasmFluid.Flowing());

    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of("calcium", name), flowableFluid);
    }

    public static void initialize() {}

}