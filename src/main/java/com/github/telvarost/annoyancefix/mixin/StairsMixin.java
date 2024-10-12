package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StairsBlock.class)
class StairsMixin extends Block {

    public StairsMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "onBreak",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_onBlockRemoved(World level, int i, int j, int k, CallbackInfo ci) {
        if (Config.config.stairFixesEnabled) {
            ci.cancel();
        }
    }

    @Inject(
            method = "onDestroyedByExplosion",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_onDestroyedByExplosion(World level, int i, int j, int k, CallbackInfo ci) {
        if (Config.config.stairFixesEnabled) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "getDroppedItemId", cancellable = true)
    public void annoyanceFix_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (Config.config.stairFixesEnabled) {
            cir.setReturnValue(id);
        }
    }

    @Inject(
            method = "dropStacks",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_beforeDestroyedByExplosion(World arg, int i, int j, int k, int l, float f, CallbackInfo ci) {
        if (Config.config.stairFixesEnabled) {
            super.dropStacks(arg, i, j, k, l, f);
            ci.cancel();
        }
    }
}

