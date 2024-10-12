package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemBaseMixin {

    @Inject(
            method = "isSuitableFor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_isEffectiveOn(Block par1, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.woodenSlabFixesEnabled) {
            return;
        }

        if (ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_WOODEN == ModHelper.ModHelperFields.blockType) {
            cir.setReturnValue(true);
        }
    }

}
