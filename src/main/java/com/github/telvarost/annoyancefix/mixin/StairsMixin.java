package com.github.telvarost.annoyancefix.mixin;


import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Stairs;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Stairs.class)
class StairsMixin extends BlockBase {

    public StairsMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "onBlockRemoved",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_onBlockRemoved(Level level, int i, int j, int k, CallbackInfo ci) {
        if (Config.ConfigFields.stairFixesEnabled) {
            ci.cancel();
        }
    }

    @Inject(
            method = "onDestroyedByExplosion",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_onDestroyedByExplosion(Level level, int i, int j, int k, CallbackInfo ci) {
        if (Config.ConfigFields.stairFixesEnabled) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "getDropId", cancellable = true)
    public void annoyanceFix_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (Config.ConfigFields.stairFixesEnabled) {
            cir.setReturnValue(id);
        }
    }

    @Inject(
            method = "beforeDestroyedByExplosion",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_beforeDestroyedByExplosion(Level arg, int i, int j, int k, int l, float f, CallbackInfo ci) {
        if (Config.ConfigFields.stairFixesEnabled) {
            super.beforeDestroyedByExplosion(arg, i, j, k, l, f);
            ci.cancel();
        }
    }
}

