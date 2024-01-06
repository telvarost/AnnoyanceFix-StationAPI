package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.PressurePlate;
import net.minecraft.block.PressurePlateTrigger;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PressurePlate.class)
class PressurePlateMixin extends BlockBase {
    @Shadow
    private PressurePlateTrigger field_1743;

    public PressurePlateMixin(int i, int j, PressurePlateTrigger arg, Material arg2) {
        super(i, j, arg2);
        this.field_1743 = arg;
        this.setTicksRandomly(true);
        float var5 = 0.0625F;
        this.setBoundingBox(var5, 0.0F, var5, 1.0F - var5, 0.03125F, 1.0F - var5);
    }

    @Redirect(
            method = "canPlaceAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;canSuffocate(III)Z"
            )
    )
    public boolean annoyanceFix_canPlaceAt(Level arg, int i, int j, int k) {
        if (Config.ConfigFields.pressurePlateFixesEnabled) {
            return true;
        }
        else
        {
            return arg.canSuffocate(i, j, k);
        }
    }

    @Inject(at = @At("HEAD"), method = "onAdjacentBlockUpdate", cancellable = true)
    public void annoyanceFix_onAdjacentBlockUpdate(Level arg, int i, int j, int k, int l, CallbackInfo ci) {
        if (Config.ConfigFields.pressurePlateFixesEnabled) {
            ci.cancel();
        }
    }
}
