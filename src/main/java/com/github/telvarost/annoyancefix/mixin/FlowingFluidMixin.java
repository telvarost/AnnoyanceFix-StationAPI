package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.FlowingFluid;
import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowingFluid.class)
class FlowingFluidMixin extends Fluid {
    public FlowingFluidMixin(int i, Material arg) {
        super(i, arg);
    }

    @Redirect(
            method = "onScheduledTick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/FlowingFluid;material:Lnet/minecraft/block/material/Material;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 3
            )
    )
    private Material annoyanceFix_allowLavaToDisappear(FlowingFluid instance) {
        if (Config.ConfigFields.lavaFixesEnabled) {
            return Material.WATER;
        } else {
            return instance.material;
        }
    }
}
