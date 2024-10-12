package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteel.class)
class FlintAndSteelMixin extends Item {

    public FlintAndSteelMixin(int i) {
        super(i);
        this.maxCount = 1;
        this.setMaxDamage(64);
    }


    @ModifyConstant(
            method = "useOnBlock",
            constant = @Constant(intValue = 1, ordinal = 1)
    )
    private int annoyanceFix_skipFlintDamage(int constant) {
        if (Config.config.flintAndSteelFixesEnabled) {
            return 0;
        } else {
            return 1;
        }
    }

    @Inject(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockId(III)I"
            )
    )
    private void annoyanceFix_useOnTile(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.flintAndSteelFixesEnabled) {
            return;
        }

        if (Block.FIRE.id == arg3.getBlockId(i, j, k)) {
            ModHelper.ModHelperFields.isFireLit = true;
        } else {
            ModHelper.ModHelperFields.isFireLit = false;
        }
    }

    @Inject(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/Entity;)V"
            )
    )
    private void annoyanceFix_useOnTileApplyDamage(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.flintAndSteelFixesEnabled) {
            return;
        }

        if (Block.FIRE.id == arg3.getBlockId(i, j, k)) {
            if (!ModHelper.ModHelperFields.isFireLit) {
                arg.damage(1, arg2);
            }
        }
    }
}
