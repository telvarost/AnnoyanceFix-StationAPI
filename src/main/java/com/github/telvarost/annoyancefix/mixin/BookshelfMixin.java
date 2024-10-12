package com.github.telvarost.annoyancefix.mixin;


import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(BookshelfBlock.class)
class BookshelfMixin extends Block {
    public BookshelfMixin(int i, Material arg) {
        super(i, arg);
    }

    @ModifyConstant(
            method = "getDroppedItemCount",
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
    public int getDroppedItemId(int i, Random random) {
        if (Config.config.bookshelfFixesEnabled) {
            return Item.BOOK.id;
        } else {
            return this.id;
        }
    }
}

