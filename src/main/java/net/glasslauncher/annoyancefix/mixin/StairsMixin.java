package net.glasslauncher.annoyancefix.mixin;


import net.minecraft.block.BlockBase;
import net.minecraft.block.Stairs;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = Stairs.class)
public abstract class StairsMixin extends BlockBase {
    @Shadow private BlockBase template;

    protected StairsMixin(int i, Material arg) {
        super(i, arg);
    }

    protected StairsMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    @Inject(at = @At("HEAD"), method = "onBlockRemoved", cancellable = true)
    public void onBlockRemoved(Level level, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "onDestroyedByExplosion", cancellable = true)
    public void onDestroyedByExplosion(Level level, int i, int j, int k, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "getDropId", cancellable = true)
    public void getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }

    @Inject(at = @At("HEAD"), method = "beforeDestroyedByExplosion", cancellable = true)
    public void beforeDestroyedByExplosion(Level arg, int i, int j, int k, int l, float f, CallbackInfo ci) {
        if (!arg.isServerSide) {
            int var7 = this.getDropCount(arg.rand);

            for(int var8 = 0; var8 < var7; ++var8) {
                if (!(arg.rand.nextFloat() > f)) {
                    int var9 = this.getDropId(l, arg.rand);
                    if (var9 > 0) {
                        this.drop(arg, i, j, k, new ItemInstance(var9, 1, this.droppedMeta(l)));
                    }
                }
            }
        }

        ci.cancel();
    }
}

