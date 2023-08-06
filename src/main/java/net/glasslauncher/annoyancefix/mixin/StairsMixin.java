package net.glasslauncher.annoyancefix.mixin;


import net.minecraft.block.BlockBase;
import net.minecraft.block.Stairs;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    public void annoyancefix_onBlockRemoved(Level level, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(
            method = "onDestroyedByExplosion",
            at = @At("HEAD"),
            cancellable = true
    )
    public void annoyancefix_onDestroyedByExplosion(Level level, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(
            method = "getDropId",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockBase;getDropId(ILjava/util/Random;)I"
            )
    )
    public int annoyancefix_getDropId(BlockBase instance, int meta, Random random) {
        return id;
    }

    @Inject(
            method = "beforeDestroyedByExplosion",
            at = @At("HEAD"),
            cancellable = true
    )
    public void annoyancefix_beforeDestroyedByExplosion(Level arg, int i, int j, int k, int l, float f, CallbackInfo ci) {
        super.beforeDestroyedByExplosion(arg, i, j, k, l, f);
        ci.cancel();
    }
}

