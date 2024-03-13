package com.github.telvarost.annoyancefix.mixin;


import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Bookshelf;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(Bookshelf.class)
class BookshelfMixin extends BlockBase {
    public BookshelfMixin(int i, Material arg) {
        super(i, arg);
    }

    @ModifyConstant(
            method = "getDropCount",
            constant = @Constant(intValue = 0)
    )
    private int annoyanceFix_getDropCount(int constant) {
        if (Config.config.bookshelfFixesEnabled) {
            return 3;
        } else {
            return 0;
        }
    }

    @Override
    public int getDropId(int i, Random random) {
        if (Config.config.bookshelfFixesEnabled) {
            return ItemBase.book.id;
        } else {
            return this.id;
        }
    }
}

