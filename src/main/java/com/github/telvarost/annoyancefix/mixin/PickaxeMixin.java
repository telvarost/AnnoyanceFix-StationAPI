package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Pickaxe;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

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

        ArrayList effectiveBlockList = new ArrayList<BlockBase>();

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnDispenser) {
            effectiveBlockList.add(BlockBase.DISPENSER);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnNormalRails) {
            effectiveBlockList.add(BlockBase.RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnGoldenRails) {
            effectiveBlockList.add(BlockBase.GOLDEN_RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnDetectorRails) {
            effectiveBlockList.add(BlockBase.DETECTOR_RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnFurnace) {
            effectiveBlockList.add(BlockBase.FURNACE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnFurnaceLit) {
            effectiveBlockList.add(BlockBase.FURNACE_LIT);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnCobblestoneStairs) {
            effectiveBlockList.add(BlockBase.COBBLESTONE_STAIRS);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnStonePressurePlate) {
            effectiveBlockList.add(BlockBase.STONE_PRESSURE_PLATE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnIronDoor) {
            effectiveBlockList.add(BlockBase.IRON_DOOR);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnRedstoneOre) {
            effectiveBlockList.add(BlockBase.REDSTONE_ORE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnRedstoneOreLit) {
            effectiveBlockList.add(BlockBase.REDSTONE_ORE_LIT);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnStoneButton) {
            effectiveBlockList.add(BlockBase.BUTTON);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnBricks) {
            effectiveBlockList.add(BlockBase.BRICKS);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnMobSpawner) {
            effectiveBlockList.add(BlockBase.MOB_SPAWNER);
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


