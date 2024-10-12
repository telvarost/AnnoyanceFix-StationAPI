package com.github.telvarost.annoyancefix.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlabBlockItem.class)
public class StoneSlabItemMixin extends BlockItem {
    public StoneSlabItemMixin(int i) {
        super(i);
    }

    @Override
    public boolean useOnBlock(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l) {
        if (arg3.getBlockId(i, j, k) == Block.SLAB.id && arg3.getBlockMeta(i, j, k) == arg.getDamage()) {
            if (arg.count == 0) {
                return false;
            } else {
                Block var8 = Block.BLOCKS[Block.DOUBLE_SLAB.id];
                if (arg3.setBlock(i, j, k, Block.DOUBLE_SLAB.id, this.getPlacementMetadata(arg.getDamage()))) {
                    Block.BLOCKS[Block.DOUBLE_SLAB.id].onPlaced(arg3, i, j, k, l);
                    Block.BLOCKS[Block.DOUBLE_SLAB.id].onPlaced(arg3, i, j, k, arg2);
                    arg3.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), var8.soundGroup.getSound(), (var8.soundGroup.getVolume() + 1.0F) / 2.0F, var8.soundGroup.getPitch() * 0.8F);
                    --arg.count;
                }

                return true;
            }
        }

        if (arg3.getBlockId(i, j, k) == Block.SNOW.id) {
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
        } else if (j == 127 && Block.BLOCKS[this.id].material.isSolid()) {
            return false;
        } else if (arg3.canPlace(this.id, i, j, k, false, l)) {
            Block var8 = Block.BLOCKS[this.id];
            if (arg3.setBlock(i, j, k, this.id, this.getPlacementMetadata(arg.getDamage()))) {
                Block.BLOCKS[this.id].onPlaced(arg3, i, j, k, l);
                Block.BLOCKS[this.id].onPlaced(arg3, i, j, k, arg2);
                arg3.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), var8.soundGroup.getSound(), (var8.soundGroup.getVolume() + 1.0F) / 2.0F, var8.soundGroup.getPitch() * 0.8F);
                --arg.count;
            }

            return true;
        } else {
            return false;
        }
    }
}
