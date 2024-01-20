package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolBase.class)
public class ToolBaseMixin extends ItemBase {

    @Shadow private BlockBase[] effectiveBlocksBase;
    @Shadow private float field_2713;
    @Shadow private int field_2714;
    @Shadow protected ToolMaterial toolMaterial;

    public ToolBaseMixin(int i, int j, ToolMaterial arg, BlockBase[] args) {
        super(i);
        this.toolMaterial = arg;
        this.effectiveBlocksBase = args;
        this.maxStackSize = 1;
        this.setDurability(arg.getDurability());
        this.field_2713 = arg.getMiningSpeed();
        this.field_2714 = j + arg.getAttackDamage();
    }

    @Inject(
            method = "getStrengthOnBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    public void annoyanceFix_getStrengthOnBlock(ItemInstance arg, BlockBase arg2, CallbackInfoReturnable<Float> cir) {
        if (!Config.ConfigFields.woodenSlabFixesEnabled)
        {
            return;
        }

        if (ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_WOODEN == ModHelper.ModHelperFields.blockType) {
            boolean isEffective = false;

            for(int var3 = 0; var3 < this.effectiveBlocksBase.length; ++var3) {
                if (this.effectiveBlocksBase[var3] == BlockBase.WOOD) {
                    isEffective = true;
                }
            }

            if (isEffective)
            {
                cir.setReturnValue(this.field_2713);
            }
            else
            {
                cir.setReturnValue(1.0F);
            }
        }
    }
}
