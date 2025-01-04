package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.google.common.collect.ObjectArrays;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

@Mixin(AxeItem.class)
class HatchetMixin extends ToolItem {
    @Shadow private static Block[] axeEffectiveBlocks;

    public HatchetMixin(int i, int j, ToolMaterial arg, Block[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/AxeItem;axeEffectiveBlocks:[Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {

        ArrayList effectiveBlockList = new ArrayList<Block>();

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWorkbench) {
            effectiveBlockList.add(Block.CRAFTING_TABLE);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnNoteblock) {
            effectiveBlockList.add(Block.NOTE_BLOCK);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodDoor) {
            effectiveBlockList.add(Block.DOOR);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnLadders) {
            effectiveBlockList.add(Block.LADDER);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnSigns) {
            effectiveBlockList.add(Block.SIGN);
            effectiveBlockList.add(Block.WALL_SIGN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodPressurePlate) {
            effectiveBlockList.add(Block.STONE_PRESSURE_PLATE);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnJukebox) {
            effectiveBlockList.add(Block.JUKEBOX);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodStairs) {
            effectiveBlockList.add(Block.WOODEN_STAIRS);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnFence) {
            effectiveBlockList.add(Block.FENCE);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnPumpkin) {
            effectiveBlockList.add(Block.PUMPKIN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnJackOLantern) {
            effectiveBlockList.add(Block.JACK_O_LANTERN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnTrapdoor) {
            effectiveBlockList.add(Block.TRAPDOOR);
        }

        Object[] objectArray = effectiveBlockList.toArray();
        Block[] blockArray = new Block[objectArray.length];

        for (int objectIndex = 0; objectIndex < objectArray.length; objectIndex++) {
            blockArray[objectIndex] = (Block) objectArray[objectIndex];
        }

        if (0 < blockArray.length) {
            axeEffectiveBlocks = ObjectArrays.concat(axeEffectiveBlocks, blockArray, Block.class);
        }
    }
}