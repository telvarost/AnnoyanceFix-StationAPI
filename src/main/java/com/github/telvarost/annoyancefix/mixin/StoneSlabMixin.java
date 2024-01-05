package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.StoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StoneSlab.class)
class StoneSlabMixin extends BlockBase {

    @Shadow private boolean field_2324;

    public StoneSlabMixin(int i, boolean bl) {
        super(i, 6, Material.STONE);
        this.field_2324 = bl;
        if (!bl) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.setLightOpacity(255);
    }
}

