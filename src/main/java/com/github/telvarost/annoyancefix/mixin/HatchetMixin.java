package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModData;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.tool.Hatchet;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hatchet.class)
class HatchetMixin extends ToolBase {
    @Shadow private static BlockBase[] effectiveBlocks;

    public HatchetMixin(int i, int j, ToolMaterial arg, BlockBase[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/tool/Hatchet;effectiveBlocks:[Lnet/minecraft/block/BlockBase;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {
        effectiveBlocks = ObjectArrays.concat(effectiveBlocks, new BlockBase[]
                { BlockBase.WORKBENCH
                , BlockBase.NOTEBLOCK
                , BlockBase.WOOD_DOOR
                , BlockBase.LADDER
                , BlockBase.STANDING_SIGN
                , BlockBase.WALL_SIGN
                , BlockBase.WOODEN_PRESSURE_PLATE
                , BlockBase.JUKEBOX
                , BlockBase.WOOD_STAIRS
                , BlockBase.FENCE
                , BlockBase.PUMPKIN
                , BlockBase.JACK_O_LANTERN
                , BlockBase.TRAPDOOR
        }, BlockBase.class);
    }
}