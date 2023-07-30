package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Pickaxe;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Pickaxe.class)
public abstract class PickaxeMixin extends ToolBase
{
    @Shadow  static BlockBase[] effectiveBlocks = new BlockBase[]
            { BlockBase.COBBLESTONE
            , BlockBase.STONE_SLAB
            , BlockBase.DOUBLE_STONE_SLAB
            , BlockBase.STONE
            , BlockBase.SANDSTONE
            , BlockBase.MOSSY_COBBLESTONE
            , BlockBase.IRON_ORE
            , BlockBase.IRON_BLOCK
            , BlockBase.COAL_ORE
            , BlockBase.GOLD_BLOCK
            , BlockBase.GOLD_ORE
            , BlockBase.DIAMOND_ORE
            , BlockBase.DIAMOND_BLOCK
            , BlockBase.ICE
            , BlockBase.NETHERRACK
            , BlockBase.LAPIS_LAZULI_ORE
            , BlockBase.LAPIS_LAZULI_BLOCK
            , BlockBase.DISPENSER
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
            };

    protected PickaxeMixin(int i, ToolMaterial arg) {
        super(i, 2, arg, effectiveBlocks);
    }
}


