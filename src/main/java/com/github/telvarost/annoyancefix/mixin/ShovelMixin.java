package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.google.common.collect.ObjectArrays;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

@Mixin(ShovelItem.class)
class ShovelMixin extends ToolItem {
    @Shadow private static Block[] shovelEffectiveBlocks;

    public ShovelMixin(int i, int j, ToolMaterial arg, Block[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/ShovelItem;shovelEffectiveBlocks:[Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {

        ArrayList effectiveBlockList = new ArrayList<Block>();

        if (Config.config.SHOVELS_CONFIG.enableShovelsEffectiveOnSoulSand) {
            effectiveBlockList.add(Block.SOUL_SAND);
        }

        Object[] objectArray = effectiveBlockList.toArray();
        Block[] blockArray = new Block[objectArray.length];

        for (int objectIndex = 0; objectIndex < objectArray.length; objectIndex++) {
            blockArray[objectIndex] = (Block) objectArray[objectIndex];
        }

        if (0 < blockArray.length) {
            shovelEffectiveBlocks = ObjectArrays.concat(shovelEffectiveBlocks, blockArray, Block.class);
        }
    }
}