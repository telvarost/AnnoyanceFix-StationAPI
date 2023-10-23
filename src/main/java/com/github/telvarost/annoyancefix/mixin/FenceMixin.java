package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Fence;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fence.class)
class FenceMixin extends BlockBase {
    public FenceMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "canPlaceAt",
            at = @At("RETURN"),
            cancellable = true
    )
    private void annoyanceFix_canPlaceAt(Level arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.fenceFixesEnabled) {
            cir.setReturnValue(true);
        }
    }

    private Box annoyanceFix_customFenceBox(Level level, int x, int y, int z) {
        int fenceId = BlockBase.FENCE.id;
        boolean posX = level.getTileId(x + 1, y, z) == fenceId;
        boolean negX = level.getTileId(x - 1, y, z) == fenceId;
        boolean posZ = level.getTileId(x, y, z + 1) == fenceId;
        boolean negZ = level.getTileId(x, y, z - 1) == fenceId;

        return Box.create(negX ? 0 : 0.375F, 0.F, negZ ? 0.F : 0.375F, posX ? 1.F : 0.625F, 1.F, posZ ? 1.F : 0.625F);
    }

    private Box annoyanceFix_customFenceBox(Level level, int x, int y, int z, boolean collider) {
        Box box = annoyanceFix_customFenceBox(level, x, y, z);

        box.minX += x;
        box.minY += y;
        box.minZ += z;
        box.maxX += x;
        box.maxY += y;
        box.maxZ += z;

        if (collider) {
            box.maxY += 0.5F;
        }

        return box;
    }

    @Inject(method = "getCollisionShape", at = @At("RETURN"), cancellable = true)
    public void annoyanceFix_getCollisionShape(Level level, int x, int y, int z, CallbackInfoReturnable<Box> cir) {
        if (Config.ConfigFields.fenceFixesEnabled) {
            cir.setReturnValue(annoyanceFix_customFenceBox(level, x, y, z, true));
        }
    }

    private static Level level;

    @Override
    public Box getOutlineShape(Level level, int x, int y, int z) {
        if (Config.ConfigFields.fenceFixesEnabled) {
            FenceMixin.level = level;
            return annoyanceFix_customFenceBox(level, x, y, z, false);
        }
        else {
            return super.getOutlineShape(level, x, y, z);
        }
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        if (Config.ConfigFields.fenceFixesEnabled) {
            if (level == null)
                return;
            Box box = annoyanceFix_customFenceBox(level, x, y, z);
            setBoundingBox((float) box.minX, (float) box.minY, (float) box.minZ, (float) box.maxX, (float) box.maxY, (float) box.maxZ);
        }
        else {
            super.updateBoundingBox(blockView, x, y, z);
        }
    }
}
