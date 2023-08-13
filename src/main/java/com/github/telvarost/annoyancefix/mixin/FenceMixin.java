package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Fence;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fence.class)
class FenceMixin extends BlockBase {
    public FenceMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "canPlaceAt",
            at = @At("RETURN"),
            cancellable = true
    )
    private void annoyanceFix_canPlaceAt(Level arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.fenceFixesEnabled) {
            cir.setReturnValue(true);
        }
    }
}
