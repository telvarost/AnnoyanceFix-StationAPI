package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolItem.class)
public class ToolBaseMixin extends Item {

    @Shadow private Block[] effectiveOnBlocks;
    @Shadow private float miningSpeed;
    @Shadow private int damage;
    @Shadow protected ToolMaterial toolMaterial;

    public ToolBaseMixin(int i, int j, ToolMaterial arg, Block[] args) {
        super(i);
        this.toolMaterial = arg;
        this.effectiveOnBlocks = args;
        this.maxCount = 1;
        this.setMaxDamage(arg.getDurability());
        this.miningSpeed = arg.getMiningSpeedMultiplier();
        this.damage = j + arg.getAttackDamage();
    }

    @Inject(
            method = "getMiningSpeedMultiplier",
            at = @At("HEAD"),
            cancellable = true
    )
    public void annoyanceFix_getStrengthOnBlock(ItemStack arg, Block arg2, CallbackInfoReturnable<Float> cir) {
        if (!Config.config.woodenSlabFixesEnabled)
        {
            return;
        }

        if (ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_WOODEN == ModHelper.ModHelperFields.blockType) {
            boolean isEffective = false;

            for(int var3 = 0; var3 < this.effectiveOnBlocks.length; ++var3) {
                if (this.effectiveOnBlocks[var3] == Block.PLANKS) {
                    isEffective = true;
                }
            }

            if (isEffective)
            {
                cir.setReturnValue(this.miningSpeed);
            }
            else
            {
                cir.setReturnValue(1.0F);
            }
        }
    }
}
