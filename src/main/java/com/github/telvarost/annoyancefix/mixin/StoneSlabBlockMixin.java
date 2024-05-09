package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.StoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StoneSlab.class)
public class StoneSlabBlockMixin extends BlockBase {

    public StoneSlabBlockMixin(int i, boolean bl) {
        super(i, 6, Material.STONE);
    }

    @Inject(
            method = "onBlockPlaced",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onBlockPlaced(Level arg, int i, int j, int k, CallbackInfo ci) {
        if (Config.config.slabPlacementFixesEnabled) {
            super.onBlockPlaced(arg, i, j, k);
            ci.cancel();
        }
    }
}
