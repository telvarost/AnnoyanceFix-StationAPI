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
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

@Mixin(PickaxeItem.class)
class PickaxeMixin extends ToolItem {
    @Shadow private static Block[] pickaxeEffectiveBlocks;

    public PickaxeMixin(int i, int j, ToolMaterial arg, Block[] args) {
        super(i, j, arg, args);
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/PickaxeItem;pickaxeEffectiveBlocks:[Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void annoyanceFix_appendExtraBlocks(CallbackInfo ci) {

        ArrayList effectiveBlockList = new ArrayList<Block>();

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnDispenser) {
            effectiveBlockList.add(Block.DISPENSER);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnNormalRails) {
            effectiveBlockList.add(Block.RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnGoldenRails) {
            effectiveBlockList.add(Block.POWERED_RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnDetectorRails) {
            effectiveBlockList.add(Block.DETECTOR_RAIL);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnFurnace) {
            effectiveBlockList.add(Block.FURNACE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnFurnaceLit) {
            effectiveBlockList.add(Block.LIT_FURNACE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnCobblestoneStairs) {
            effectiveBlockList.add(Block.COBBLESTONE_STAIRS);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnStonePressurePlate) {
            effectiveBlockList.add(Block.WOODEN_PRESSURE_PLATE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnIronDoor) {
            effectiveBlockList.add(Block.IRON_DOOR);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnRedstoneOre) {
            effectiveBlockList.add(Block.REDSTONE_ORE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnRedstoneOreLit) {
            effectiveBlockList.add(Block.LIT_REDSTONE_ORE);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnStoneButton) {
            effectiveBlockList.add(Block.BUTTON);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnBricks) {
            effectiveBlockList.add(Block.BRICKS);
        }

        if (Config.config.PICKAXES_CONFIG.enablePickaxesEffectiveOnMobSpawner) {
            effectiveBlockList.add(Block.SPAWNER);
        }

        Object[] objectArray = effectiveBlockList.toArray();
        Block[] blockArray = new Block[objectArray.length];

        for (int objectIndex = 0; objectIndex < objectArray.length; objectIndex++) {
            blockArray[objectIndex] = (Block) objectArray[objectIndex];
        }

        if (0 < blockArray.length) {
            pickaxeEffectiveBlocks = ObjectArrays.concat(pickaxeEffectiveBlocks, blockArray, Block.class);
        }
    }
}


