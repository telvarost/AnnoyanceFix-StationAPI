package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlabBlock.class)
public class StoneSlabBlockMixin extends Block {

    public StoneSlabBlockMixin(int i, boolean bl) {
        super(i, 6, Material.STONE);
    }

    @Inject(
            method = "onPlaced",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onBlockPlaced(World arg, int i, int j, int k, CallbackInfo ci) {
        if (Config.config.slabPlacementFixesEnabled) {
            super.onPlaced(arg, i, j, k);
            ci.cancel();
        }
    }
}
