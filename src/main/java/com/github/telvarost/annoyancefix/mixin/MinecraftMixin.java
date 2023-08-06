package com.github.telvarost.annoyancefix.mixin;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import com.github.telvarost.annoyancefix.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Minecart;
import net.minecraft.entity.Painting;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitType;
import net.modificationstation.stationapi.api.util.Util;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Minecraft.class)
class MinecraftMixin {
    @Unique
    private final Int2IntMap annoyanceFix_pickBlockLookupMap = Util.make(new Int2IntOpenHashMap(), map -> {
        map.put(BlockBase.REDSTONE_DUST.id, ItemBase.redstoneDust.id);
        map.put(BlockBase.REDSTONE_REPEATER.id, ItemBase.redstoneRepeater.id);
        map.put(BlockBase.REDSTONE_REPEATER_LIT.id, ItemBase.redstoneRepeater.id);
        map.put(BlockBase.WOOD_DOOR.id, ItemBase.woodDoor.id);
        map.put(BlockBase.IRON_DOOR.id, ItemBase.ironDoor.id);
        map.put(BlockBase.STANDING_SIGN.id, ItemBase.sign.id);
        map.put(BlockBase.WALL_SIGN.id, ItemBase.sign.id);
        map.put(BlockBase.CROPS.id, ItemBase.seeds.id);
        map.put(BlockBase.BED.id, ItemBase.bed.id);
        map.put(BlockBase.CAKE.id, ItemBase.cake.id);
    });


    @Shadow public HitResult hitResult;
    @Shadow public Level level;

    /**
     * Adds functionality for picking blocks that are not in the hotbar.
     * It also adds pick block functionality to boats, minecarts and paintings.
     * Injected at the head of method_2103, which is the pick block method.
     *
     * @return the selected id
     */
    @ModifyVariable(
            method = "method_2103",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/entity/player/AbstractClientPlayer;",
                    opcode = Opcodes.GETFIELD
            ),
            index = 1
    )
    private int annoyanceFix_pickBlock(int vanillaItemId) {
        if (Config.ConfigFields.pickBlockFixesEnabled) {
            int itemID = 0;
            // field_790 means "Entity"
            if (this.hitResult.type == HitType.field_790) {
                itemID = annoyanceFix_getItemIDFromEntity(hitResult.field_1989);
            }

            // field_789 means "Tile"
            if (this.hitResult.type == HitType.field_789) {
                int tileDamage = this.level.getTileMeta(hitResult.x, hitResult.y, hitResult.z);
                int tileID = this.level.getTileId(hitResult.x, hitResult.y, hitResult.z);
                itemID = annoyanceFix_getItemIDFromTileID(tileID, tileDamage);
            }

            if (itemID == 0) {
                // No item found, let vanilla minecraft handle it
                return vanillaItemId;
            }
            // Successfully selected an item, return the new id
            return itemID;
        }
        else
        {
            return vanillaItemId;
        }
    }

    /**
     * Returns a corresponding item ID for a given tile.
     * For example, when looking at crops, it should return the seeds item.
     *
     * @param tileID the ID of the tile
     * @return the corresponding item ID
     */
    @Unique
    private int annoyanceFix_getItemIDFromTileID(int tileID, int tileDamage) {
        // Special cases
        if (tileID == BlockBase.PISTON_HEAD.id) {
            // 0-5 damage = normal piston
            // 8-13 damage = sticky piston
            if (tileDamage < 8) {
                return BlockBase.PISTON.id;
            } else {
                return BlockBase.STICKY_PISTON.id;
            }
        }

        if (!annoyanceFix_pickBlockLookupMap.containsKey(tileID)) {
            return 0;
        }
        return annoyanceFix_pickBlockLookupMap.get(tileID);
    }

    /**
     * Returns a corresponding item ID for a given entity.
     * For example, when looking at a boat, it should return a boat.
     *
     * @param entity the entity to check
     * @return the corresponding item ID
     */
    @Unique
    private int annoyanceFix_getItemIDFromEntity(EntityBase entity) {
        if (entity instanceof Painting) {
            return ItemBase.painting.id;
        }

        if (entity instanceof Boat) {
            return ItemBase.boat.id;
        }

        if (entity instanceof Minecart minecart) {
            return switch (minecart.type) {
                case 1 -> ItemBase.minecartChest.id;
                case 2 -> ItemBase.minecartFurnace.id;
                default -> ItemBase.minecart.id;
            };
        }

        return 0;
    }
}
