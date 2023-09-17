package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModData;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBase.class)
public class ItemBaseMixin {

    @Inject(
            method = "isEffectiveOn",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_isEffectiveOn(BlockBase par1, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.ConfigFields.woodenSlabFixesEnabled) {
            return;
        }

        if (!ModData.ModDataFields.isBlockMetaDataValue2) {
            return;
        }

        boolean isStoneSlab = par1.id == BlockBase.STONE_SLAB.id || par1.id == BlockBase.DOUBLE_STONE_SLAB.id;
        if (!isStoneSlab) {
            return;
        }

        cir.setReturnValue(true);
    }

}
