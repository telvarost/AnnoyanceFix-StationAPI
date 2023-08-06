package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Fence;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fence.class)
abstract class FenceMixin extends BlockBase {
    public FenceMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "canPlaceAt",
            at = @At("RETURN"),
            cancellable = true
    )
    private void annoyancefix_canPlaceAt(Level arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
