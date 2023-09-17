package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModData;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.entity.player.StationFlatteningPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PlayerBase.class, priority = 1001)
public class PlayerBaseMixin extends Living implements StationFlatteningPlayerEntity {
    @Shadow public PlayerInventory inventory;

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }

//    @Override
//    public boolean canHarvest(BlockState state) {
//        if (  (Config.ConfigFields.woodenSlabFixesEnabled)
//           && (ModData.ModDataFields.isBlockMetaDataValue2)
//           && (  (BlockBase.STONE_SLAB.id != state.getBlock().id)
//              || (BlockBase.DOUBLE_STONE_SLAB.id != state.getBlock().id)
//              )
//        ) {
//            return true;
//        }
//        else
//        {
//            return inventory.canHarvest(state);
//        }
//    }
}