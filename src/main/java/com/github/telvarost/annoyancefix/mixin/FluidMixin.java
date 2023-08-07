package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.FlowingFluid;
import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(FlowingFluid.class)
class FlowingFluidMixin extends Fluid {

    @Shadow
    int field_1232 = 0;

    @Shadow
    boolean[] field_1233 = new boolean[4];

    @Shadow
    int[] field_1234 = new int[4];


    public FlowingFluidMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "onScheduledTick",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onScheduledTick(Level arg, int i, int j, int k, Random random, CallbackInfo ci) {
        if (Config.ConfigFields.lavaFixesEnabled) {

            ci.cancel();
        }
    }
}
