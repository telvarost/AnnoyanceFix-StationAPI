package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Cobweb;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Cobweb.class)
class CobwebMixin extends BlockBase {

    @Unique
    private boolean brokenByShears = false;

    public CobwebMixin(int i, Material arg) {
        super(i, arg);
    }

    @Override
    public void afterBreak(Level arg, PlayerBase player, int i, int j, int k, int l) {

        if (Config.config.cobwebFixesEnabled) {
            brokenByShears = false;

            if (  (null != player)
               && (null != player.inventory)
               && (null != player.inventory.getHeldItem())
               && (ItemBase.shears.id == player.inventory.getHeldItem().itemId)
               )
            {
                brokenByShears = true;
            }
        }

        player.increaseStat(Stats.mineBlock[this.id], 1);
        this.drop(arg, i, j, k, l);
    }

    @Inject(at = @At("HEAD"), method = "getDropId", cancellable = true)
    public void annoyanceFix_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.cobwebFixesEnabled) {
            return;
        }

        if (brokenByShears) {
            cir.setReturnValue(id);
            brokenByShears = false;
        }
    }
}

