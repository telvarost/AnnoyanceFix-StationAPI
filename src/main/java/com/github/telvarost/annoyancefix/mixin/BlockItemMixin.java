package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Unique private boolean replaceBlock = false;

    @WrapOperation(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockId(III)I"
            )
    )
    public int annoyanceFix_useOnBlockId(World instance, int x, int y, int z, Operation<Integer> original) {
        int blockId = original.call(instance, x, y, z);

        if (  Block.GRASS.id == blockId
           || Block.DEAD_BUSH.id == blockId
        ) {
            if (Config.config.plantReplacementFixesEnabled) {
                blockId = Block.SNOW.id;
                replaceBlock = true;
            }
        }

        return blockId;
    }

    @WrapOperation(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;canPlace(IIIIZI)Z"
            )
    )
    public boolean annoyanceFix_useOnBlockCanPlace(World instance, int blockId, int x, int y, int z, boolean fallingBlock, int side, Operation<Boolean> original) {
        boolean canPlace = original.call(instance, blockId, x, y, z, fallingBlock, side);

        if (replaceBlock) {
            canPlace = true;
            replaceBlock = false;
        }

        return canPlace;
    }
}
