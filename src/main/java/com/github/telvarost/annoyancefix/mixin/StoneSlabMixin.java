package com.github.telvarost.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.Block;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.StoneSlab;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StoneSlab.class)
public class StoneSlabMixin extends Block {
    public StoneSlabMixin(int i) {
        super(i);
    }

    @Override
    public boolean useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l) {
        if (arg3.getTileId(i, j, k) == BlockBase.STONE_SLAB.id && arg3.getTileMeta(i, j, k) == arg.getDamage()) {
            if (arg.count == 0) {
                return false;
            } else {
                BlockBase var8 = BlockBase.BY_ID[BlockBase.DOUBLE_STONE_SLAB.id];
                if (arg3.placeBlockWithMetaData(i, j, k, BlockBase.DOUBLE_STONE_SLAB.id, this.getMetaData(arg.getDamage()))) {
                    BlockBase.BY_ID[BlockBase.DOUBLE_STONE_SLAB.id].onBlockPlaced(arg3, i, j, k, l);
                    BlockBase.BY_ID[BlockBase.DOUBLE_STONE_SLAB.id].afterPlaced(arg3, i, j, k, arg2);
                    arg3.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), var8.sounds.getWalkSound(), (var8.sounds.getVolume() + 1.0F) / 2.0F, var8.sounds.getPitch() * 0.8F);
                    --arg.count;
                }

                return true;
            }
        }

        if (arg3.getTileId(i, j, k) == BlockBase.SNOW.id) {
            l = 0;
        } else {
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }
        }

        if (arg.count == 0) {
            return false;
        } else if (j == 127 && BlockBase.BY_ID[this.id].material.isSolid()) {
            return false;
        } else if (arg3.canPlaceTile(this.id, i, j, k, false, l)) {
            BlockBase var8 = BlockBase.BY_ID[this.id];
            if (arg3.placeBlockWithMetaData(i, j, k, this.id, this.getMetaData(arg.getDamage()))) {
                BlockBase.BY_ID[this.id].onBlockPlaced(arg3, i, j, k, l);
                BlockBase.BY_ID[this.id].afterPlaced(arg3, i, j, k, arg2);
                arg3.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), var8.sounds.getWalkSound(), (var8.sounds.getVolume() + 1.0F) / 2.0F, var8.sounds.getPitch() * 0.8F);
                --arg.count;
            }

            return true;
        } else {
            return false;
        }
    }
}
