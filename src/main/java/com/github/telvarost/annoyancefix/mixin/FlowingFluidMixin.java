package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.FlowingLiquidBlock;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowingLiquidBlock.class)
class FlowingFluidMixin extends LiquidBlock {
    public FlowingFluidMixin(int i, Material arg) {
        super(i, arg);
    }

    /**
     * This method targets the following if statement, to prevent var10 being set to var6:
     *
     *          if(this.blockMaterial == Material.lava && var6 < 8 && var10 < 8 && var10 > var6 && var5.nextInt(4) != 0) {
     * 				var10 = var6;
     * 				var8 = false;
     *          }
     *
     * This is to allow the if statement "if(var10 != var6)" code to run which updates the flowing lava blocks
     * thereby allowing them to fade away when the lava source block is removed.
     * The exact meaning behind what var10 and var6 were intended to be is unknown.
     *
     * @param instance - instance of the flowing fluid block
     * @return - replaces the material to allow block updates on the flowing fluid when fix is active
     */
    @Redirect(
            method = "onTick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/FlowingLiquidBlock;material:Lnet/minecraft/block/material/Material;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 3
            )
    )
    private Material annoyanceFix_allowLavaToDisappear(FlowingLiquidBlock instance) {
        if (Config.config.lavaFixesEnabled) {
            return Material.WATER;
        } else {
            return instance.material;
        }
    }

    @WrapOperation(
            method = "onTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMeta(III)I"
            )
    )
    private int annoyanceFix_allowWaterSpringPropagation(World instance, int x, int y, int z, Operation<Integer> original) {
        if (Config.config.waterFixesEnabled) {
            return original.call(instance, x, y - 1, z);
        } else {
            return original.call(instance, x, y, z);
        }
    }
}
