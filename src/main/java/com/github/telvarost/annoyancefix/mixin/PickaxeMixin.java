package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Pickaxe;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(Pickaxe.class)
class PickaxeMixin extends ToolBase {
    @Shadow private static BlockBase[] effectiveBlocks;

    public PickaxeMixin(int i, int j, ToolMaterial arg, BlockBase[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/tool/Pickaxe;effectiveBlocks:[Lnet/minecraft/block/BlockBase;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {
        effectiveBlocks = ObjectArrays.concat(effectiveBlocks, new BlockBase[]
                { BlockBase.DISPENSER
                , BlockBase.RAIL
                , BlockBase.GOLDEN_RAIL
                , BlockBase.DETECTOR_RAIL
                , BlockBase.FURNACE
                , BlockBase.FURNACE_LIT
                , BlockBase.COBBLESTONE_STAIRS
                , BlockBase.STONE_PRESSURE_PLATE
                , BlockBase.IRON_DOOR
                , BlockBase.REDSTONE_ORE
                , BlockBase.REDSTONE_ORE_LIT
                , BlockBase.BUTTON
                , BlockBase.BRICKS
                , BlockBase.MOB_SPAWNER
        }, BlockBase.class);
    }
}


