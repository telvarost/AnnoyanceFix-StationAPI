package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.mixin.invoker.FlowingFluidInvoker;
import net.minecraft.block.FlowingFluid;
import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
            at = @At("HEAD"),
            cancellable = true
    )
    public void onScheduledTick(Level arg, int i, int j, int k, Random random, CallbackInfo ci) {

        if (Config.ConfigFields.lavaFixesEnabled) {
            int var6 = this.method_1220(arg, i, j, k);
            byte var7 = 1;
            if (this.material == Material.LAVA && !arg.dimension.evaporatesWater) {
                var7 = 2;
            }

            boolean var8 = true;
            int var10;
            if (var6 > 0) {
                int var9 = -100;
                this.field_1232 = 0;
                var9 = ((FlowingFluidInvoker)this).annoyanceFix_method_1053(arg, i - 1, j, k, var9);
                var9 = ((FlowingFluidInvoker)this).annoyanceFix_method_1053(arg, i + 1, j, k, var9);
                var9 = ((FlowingFluidInvoker)this).annoyanceFix_method_1053(arg, i, j, k - 1, var9);
                var9 = ((FlowingFluidInvoker)this).annoyanceFix_method_1053(arg, i, j, k + 1, var9);
                var10 = var9 + var7;
                if (var10 >= 8 || var9 < 0) {
                    var10 = -1;
                }

                if (this.method_1220(arg, i, j + 1, k) >= 0) {
                    int var11 = this.method_1220(arg, i, j + 1, k);
                    if (var11 >= 8) {
                        var10 = var11;
                    } else {
                        var10 = var11 + 8;
                    }
                }

                if (this.field_1232 >= 2 && this.material == Material.WATER) {
                    if (arg.getMaterial(i, j - 1, k).isSolid()) {
                        var10 = 0;
                    } else if (arg.getMaterial(i, j - 1, k) == this.material && arg.getTileMeta(i, j, k) == 0) {
                        var10 = 0;
                    }
                }

//                if (this.material == Material.LAVA && var6 < 8 && var10 < 8 && var10 > var6 && random.nextInt(4) != 0) {
//                    var10 = var6;
//                    var8 = false;
//                }

                if (var10 != var6) {
                    var6 = var10;
                    if (var10 < 0) {
                        arg.setTile(i, j, k, 0);
                    } else {
                        arg.setTileMeta(i, j, k, var10);
                        arg.method_216(i, j, k, this.id, this.getTickrate());
                        arg.updateAdjacentBlocks(i, j, k, this.id);
                    }
                } else if (var8) {
                    ((FlowingFluidInvoker)this).annoyanceFix_method_1055(arg, i, j, k);
                }
            } else {
                ((FlowingFluidInvoker)this).annoyanceFix_method_1055(arg, i, j, k);
            }

            if (((FlowingFluidInvoker)this).annoyanceFix_method_1058(arg, i, j - 1, k)) {
                if (var6 >= 8) {
                    arg.placeBlockWithMetaData(i, j - 1, k, this.id, var6);
                } else {
                    arg.placeBlockWithMetaData(i, j - 1, k, this.id, var6 + 8);
                }
            } else if (var6 >= 0 && (var6 == 0 || ((FlowingFluidInvoker)this).annoyanceFix_method_1057(arg, i, j - 1, k))) {
                boolean[] var12 = ((FlowingFluidInvoker)this).annoyanceFix_method_1056(arg, i, j, k);
                var10 = var6 + var7;
                if (var6 >= 8) {
                    var10 = 1;
                }

                if (var10 >= 8) {
                    return;
                }

                if (var12[0]) {
                    ((FlowingFluidInvoker)this).annoyanceFix_method_1054(arg, i - 1, j, k, var10);
                }

                if (var12[1]) {
                    ((FlowingFluidInvoker)this).annoyanceFix_method_1054(arg, i + 1, j, k, var10);
                }

                if (var12[2]) {
                    ((FlowingFluidInvoker)this).annoyanceFix_method_1054(arg, i, j, k - 1, var10);
                }

                if (var12[3]) {
                    ((FlowingFluidInvoker)this).annoyanceFix_method_1054(arg, i, j, k + 1, var10);
                }
            }

            ci.cancel();
        }
    }
}
