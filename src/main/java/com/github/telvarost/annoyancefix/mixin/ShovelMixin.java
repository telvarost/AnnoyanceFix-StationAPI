package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Shovel;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(Shovel.class)
class ShovelMixin extends ToolBase {
    @Shadow private static BlockBase[] effectiveBlocks;

    public ShovelMixin(int i, int j, ToolMaterial arg, BlockBase[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/tool/Shovel;effectiveBlocks:[Lnet/minecraft/block/BlockBase;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {

        ArrayList effectiveBlockList = new ArrayList<BlockBase>();

        if (Config.config.SHOVELS_CONFIG.enableShovelsEffectiveOnSoulSand) {
            effectiveBlockList.add(BlockBase.SOUL_SAND);
        }

        Object[] objectArray = effectiveBlockList.toArray();
        BlockBase[] blockArray = new BlockBase[objectArray.length];

        for (int objectIndex = 0; objectIndex < objectArray.length; objectIndex++) {
            blockArray[objectIndex] = (BlockBase) objectArray[objectIndex];
        }

        if (0 < blockArray.length) {
            effectiveBlocks = ObjectArrays.concat(effectiveBlocks, blockArray, BlockBase.class);
        }
    }
}