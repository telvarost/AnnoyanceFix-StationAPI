package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
class FenceMixin extends Block {
    public FenceMixin(int i, Material arg) {
        super(i, arg);
    }

    @Inject(
            method = "canPlaceAt",
            at = @At("RETURN"),
            cancellable = true
    )
    private void annoyanceFix_canPlaceAt(World arg, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.fenceFixesEnabled) {
            cir.setReturnValue(true);
        }
    }

    @Unique
    private Box annoyanceFix_customFenceBox(World level, int x, int y, int z) {
        int fenceId = Block.FENCE.id;
        boolean posX = level.getBlockId(x + 1, y, z) == fenceId;
        boolean negX = level.getBlockId(x - 1, y, z) == fenceId;
        boolean posZ = level.getBlockId(x, y, z + 1) == fenceId;
        boolean negZ = level.getBlockId(x, y, z - 1) == fenceId;

        return Box.create(negX ? 0 : 0.375F, 0.F, negZ ? 0.F : 0.375F, posX ? 1.F : 0.625F, 1.F, posZ ? 1.F : 0.625F);
    }

    @Unique
    private Box annoyanceFix_customFenceBox(World level, int x, int y, int z, boolean collider) {
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
    public void annoyanceFix_getCollisionShape(World level, int x, int y, int z, CallbackInfoReturnable<Box> cir) {
        if (Config.config.fenceShapeFixesEnabled) {
            cir.setReturnValue(annoyanceFix_customFenceBox(level, x, y, z, true));
        }
    }

    @Unique
    private static World level;

    @Override
    public Box getBoundingBox(World level, int x, int y, int z) {
        if (Config.config.fenceShapeFixesEnabled) {
            FenceMixin.level = level;
            return annoyanceFix_customFenceBox(level, x, y, z, false);
        }
        else {
            return super.getBoundingBox(level, x, y, z);
        }
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        if (Config.config.fenceShapeFixesEnabled) {
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
