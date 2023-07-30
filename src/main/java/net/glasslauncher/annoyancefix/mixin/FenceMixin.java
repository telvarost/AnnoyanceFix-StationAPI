package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Fence;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Fence.class)
public abstract class FenceMixin extends BlockBase {

    public FenceMixin(int i, Material arg) {
        super(i, arg);
    }

    public FenceMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    @Inject(at = @At("RETURN"), method = "canPlaceAt", cancellable = true)
    public void canPlaceAt(Level arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
