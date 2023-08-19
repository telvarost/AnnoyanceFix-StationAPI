package com.github.telvarost.annoyancefix.mixin;


import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Cobweb;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Cobweb.class)
class CobwebMixin extends BlockBase {

    public CobwebMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(at = @At("HEAD"), method = "getDropId", cancellable = true)
    public void annoyanceFix_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (!Config.ConfigFields.cobwebFixesEnabled) {
            return;
        }

        PlayerBase player = PlayerHelper.getPlayerFromGame();

        if (  (null != player)
           && (null != player.inventory)
           && (null != player.inventory.getHeldItem())
           && (ItemBase.shears.id == player.inventory.getHeldItem().itemId)
           )
        {
            cir.setReturnValue(id);
        }
    }
}

