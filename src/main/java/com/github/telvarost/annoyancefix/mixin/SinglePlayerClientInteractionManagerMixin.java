package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.SingleplayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SingleplayerInteractionManager.class)
public class SinglePlayerClientInteractionManagerMixin extends InteractionManager {

    public SinglePlayerClientInteractionManagerMixin(Minecraft minecraft) {
        super(minecraft);
    }

    /**
     * When any clicked tile has a metadata value of 2 axes effective against slabs becomes true temporarily.
     * This would occur even when tiles like wool with a metadata value of 2 are clicked,
     * but nothing would happen for those as they are not slabs so the variable would have no effect.
     *
     * @param i - tile's x location
     * @param j - tile's y location
     * @param k - tile's z location
     * @param l - unknown
     * @param ci - callback info to allow cancelling the method
     */
    @Inject(
            method = "attackBlock",
            at = @At("HEAD")
    )
    public void annoyanceFix_clickBlock(int i, int j, int k, int l, CallbackInfo ci) {
        if (!Config.config.woodenSlabFixesEnabled) {
            return;
        }

        int blockId = this.minecraft.world.getBlockId(i, j, k);
        int blockMetaData = this.minecraft.world.getBlockMeta(i, j, k);

        /** - Save some information on block type */
        if (  (Block.SLAB.id == blockId)
           || (Block.DOUBLE_SLAB.id == blockId)
           )
        {
            if (2 == blockMetaData) {
                ModHelper.ModHelperFields.blockType = ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_WOODEN;
            }
            else {
                ModHelper.ModHelperFields.blockType = ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_NOT_WOODEN;
            }
        }
        else
        {
            ModHelper.ModHelperFields.blockType = ModHelper.BlockTypeEnum.BLOCK_IS_NOT_A_SLAB;
        }
    }
}
