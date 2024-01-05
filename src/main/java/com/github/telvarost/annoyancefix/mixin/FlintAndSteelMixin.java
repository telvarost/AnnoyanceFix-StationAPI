package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.FlintAndSteel;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteel.class)
class FlintAndSteelMixin extends ItemBase {

    public FlintAndSteelMixin(int i) {
        super(i);
        this.maxStackSize = 1;
        this.setDurability(64);
    }


    @ModifyConstant(
            method = "useOnTile",
            constant = @Constant(intValue = 1, ordinal = 1)
    )
    private int annoyanceFix_skipFlintDamage(int constant) {
        if (Config.ConfigFields.flintAndSteelFixesEnabled) {
            return 0;
        } else {
            return 1;
        }
    }

    @Inject(
            method = "useOnTile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemInstance;applyDamage(ILnet/minecraft/entity/EntityBase;)V"
            )
    )
    private void annoyanceFix_useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.ConfigFields.flintAndSteelFixesEnabled) {
            return;
        }

        if (BlockBase.FIRE.id == arg3.getTileId(i, j, k))
        {
            arg.applyDamage(1, arg2);
        }
    }
}
