package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.Hatchet;
import net.minecraft.item.tool.ToolBase;
import net.minecraft.item.tool.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Hatchet.class)
public abstract class HatchetMixin extends ToolBase
{
    @Shadow
    private static BlockBase[] effectiveBlocks = new BlockBase[]
            { BlockBase.WOOD
            , BlockBase.BOOKSHELF
            , BlockBase.LOG
            , BlockBase.CHEST
            , BlockBase.WORKBENCH
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
            };

    protected HatchetMixin(int i, ToolMaterial arg) {
        super(i, 3, arg, effectiveBlocks);
    }
}