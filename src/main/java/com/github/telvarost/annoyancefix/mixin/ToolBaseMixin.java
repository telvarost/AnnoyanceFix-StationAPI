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
    @Shadow private float field_2713 = 4.0F;
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
            at = @At("TAIL"),
            cancellable = true
    )
    public void annoyanceFix_getStrengthOnBlock(ItemInstance arg, BlockBase arg2, CallbackInfoReturnable<Float> cir) {
        if (ModHelper.BlockTypeEnum.BLOCK_IS_NOT_A_SLAB == ModHelper.ModHelperFields.blockType)
        {
            return;
        }

        if (ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_NOT_WOODEN == ModHelper.ModHelperFields.blockType) {
            if (    (this.id == ItemBase.woodPickaxe.id)
                || (this.id == ItemBase.stonePickaxe.id)
                || (this.id == ItemBase.ironPickaxe.id)
                || (this.id == ItemBase.diamondPickaxe.id)
                || (this.id == ItemBase.goldPickaxe.id)
               )
            {
                cir.setReturnValue(this.field_2713);
            }
        }
        else
        {
            if (Config.ConfigFields.woodenSlabFixesEnabled)
            {
                if (    (this.id == ItemBase.woodAxe.id)
                    || (this.id == ItemBase.stoneAxe.id)
                    || (this.id == ItemBase.ironAxe.id)
                    || (this.id == ItemBase.diamondAxe.id)
                    || (this.id == ItemBase.goldAxe.id)
                ) {
                    cir.setReturnValue(this.field_2713);
                }
            }
            else
            {
                if (    (this.id == ItemBase.woodPickaxe.id)
                        || (this.id == ItemBase.stonePickaxe.id)
                        || (this.id == ItemBase.ironPickaxe.id)
                        || (this.id == ItemBase.diamondPickaxe.id)
                        || (this.id == ItemBase.goldPickaxe.id)
                )
                {
                    cir.setReturnValue(this.field_2713);
                }
            }
        }
    }
}
