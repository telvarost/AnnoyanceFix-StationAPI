package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.Block;
import net.minecraft.block.CobwebBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(CobwebBlock.class)
class CobwebMixin extends Block {

    @Unique
    private boolean brokenByShears = false;

    public CobwebMixin(int i, Material arg) {
        super(i, arg);
    }

    @Override
    public void afterBreak(World arg, PlayerEntity player, int i, int j, int k, int l) {

        if (Config.config.cobwebFixesEnabled) {
            brokenByShears = false;

            if (  (null != player)
               && (null != player.inventory)
               && (null != player.inventory.getSelectedItem())
               && (Item.SHEARS.id == player.inventory.getSelectedItem().itemId)
               )
            {
                brokenByShears = true;
            }
        }

        player.increaseStat(Stats.MINE_BLOCK[this.id], 1);
        this.dropStacks(arg, i, j, k, l);
    }

    @Inject(at = @At("HEAD"), method = "getDroppedItemId", cancellable = true)
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

