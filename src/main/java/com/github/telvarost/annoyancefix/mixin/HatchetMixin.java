package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Hatchet;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(Hatchet.class)
class HatchetMixin extends ToolBase {
    @Shadow private static BlockBase[] effectiveBlocks;

    public HatchetMixin(int i, int j, ToolMaterial arg, BlockBase[] args) {
        super(i, j, arg, args);
    }

    @Override
    public boolean isEffectiveOn(BlockBase arg) {
        if (  (Config.config.woodenSlabFixesEnabled)
           && (ModHelper.BlockTypeEnum.SLAB_BLOCK_IS_WOODEN == ModHelper.ModHelperFields.blockType)
           )
        {
            return true;
        }
        else
        {
            return false;
        }
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

        ArrayList effectiveBlockList = new ArrayList<BlockBase>();

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWorkbench) {
            effectiveBlockList.add(BlockBase.WORKBENCH);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnNoteblock) {
            effectiveBlockList.add(BlockBase.NOTEBLOCK);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodDoor) {
            effectiveBlockList.add(BlockBase.WOOD_DOOR);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnLadders) {
            effectiveBlockList.add(BlockBase.LADDER);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnSigns) {
            effectiveBlockList.add(BlockBase.STANDING_SIGN);
            effectiveBlockList.add(BlockBase.WALL_SIGN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodPressurePlate) {
            effectiveBlockList.add(BlockBase.WOODEN_PRESSURE_PLATE);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnJukebox) {
            effectiveBlockList.add(BlockBase.JUKEBOX);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnWoodStairs) {
            effectiveBlockList.add(BlockBase.WOOD_STAIRS);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnFence) {
            effectiveBlockList.add(BlockBase.FENCE);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnPumpkin) {
            effectiveBlockList.add(BlockBase.PUMPKIN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnJackOLantern) {
            effectiveBlockList.add(BlockBase.JACK_O_LANTERN);
        }

        if (Config.config.AXES_CONFIG.enableAxesEffectiveOnTrapdoor) {
            effectiveBlockList.add(BlockBase.TRAPDOOR);
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