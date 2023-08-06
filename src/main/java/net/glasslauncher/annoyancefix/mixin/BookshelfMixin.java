package net.glasslauncher.annoyancefix.mixin;


import net.glasslauncher.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Bookshelf;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = Bookshelf.class)
public abstract class BookshelfMixin extends BlockBase {

    protected BookshelfMixin(int i, Material arg) {
        super(i, arg);
    }

    protected BookshelfMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    @Inject(at = @At("HEAD"), method = "getDropCount", cancellable = true)
    public void getDropCount(Random random, CallbackInfoReturnable<Integer> cir) {
        if (Config.ConfigFields.bookshelfFixesEnabled) {
            cir.setReturnValue(3);
        }
    }

    @Override
    public int getDropId(int i, Random random) {
        if (Config.ConfigFields.bookshelfFixesEnabled) {
            return ItemBase.book.id;
        }
        else
        {
            return this.id;
        }
    }
}

