package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.BaseClientInteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.SinglePlayerClientInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SinglePlayerClientInteractionManager.class)
public class SinglePlayerClientInteractionManagerMixin extends BaseClientInteractionManager {

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
            method = "method_1707",
            at = @At("HEAD")
    )
    public void annoyanceFix_clickBlock(int i, int j, int k, int l, CallbackInfo ci) {
        if (!Config.ConfigFields.woodenSlabFixesEnabled) {
            return;
        }

        int blockMetaData = this.minecraft.level.getTileMeta(i, j, k);

        /* Check if the tile's metadata is the metadata of a wooden slab */
        if (2 == blockMetaData) {
            ModData.ModDataFields.isBlockMetaDataValue2 = true;
        }
        else {
            ModData.ModDataFields.isBlockMetaDataValue2 = false;
        }
    }
}
