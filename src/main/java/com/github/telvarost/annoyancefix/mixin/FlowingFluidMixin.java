package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.FlowingFluid;
import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowingFluid.class)
class FlowingFluidMixin extends Fluid {
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
     * This is to allow the following code to run which updates the flowing lava blocks
     * thereby allowing them to fade away when the lava source block is removed:
     *
     * 			if(var10 != var6) {
     * 			    var6 = var10;
     * 			    if(var10 < 0) {
     * 				    var1.setBlockWithNotify(var2, var3, var4, 0);
     *              } else {
     * 				    var1.setBlockMetadataWithNotify(var2, var3, var4, var10);
     * 				    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
     * 				    var1.notifyBlocksOfNeighborChange(var2, var3, var4, this.blockID);
     *              }
     *          } else if(var8) {
     * 			    this.func_30003_j(var1, var2, var3, var4);
     *          }
     *
     * @param instance - instance of the flowing fluid block
     * @return - replaces the material to allow block updates on the flowing fluid when fix is active
     */
    @Redirect(
            method = "onScheduledTick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/FlowingFluid;material:Lnet/minecraft/block/material/Material;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 3
            )
    )
    private Material annoyanceFix_allowLavaToDisappear(FlowingFluid instance) {
        if (Config.ConfigFields.lavaFixesEnabled) {
            return Material.WATER;
        } else {
            return instance.material;
        }
    }
}
