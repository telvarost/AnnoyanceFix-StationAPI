package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Util;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Minecraft.class)
class MinecraftMixin {

    @Shadow public HitResult crosshairTarget;
    @Shadow public World world;

    @Unique
    private final Int2IntMap annoyanceFix_pickBlockLookupMap = Util.make(new Int2IntOpenHashMap(), map -> {
        map.put(Block.REDSTONE_WIRE.id, Item.REDSTONE.id);
        map.put(Block.REPEATER.id, Item.REPEATER.id);
        map.put(Block.POWERED_REPEATER.id, Item.REPEATER.id);
        map.put(Block.DOOR.id, Item.WOODEN_DOOR.id);
        map.put(Block.IRON_DOOR.id, Item.IRON_DOOR.id);
        map.put(Block.SIGN.id, Item.SIGN.id);
        map.put(Block.WALL_SIGN.id, Item.SIGN.id);
        map.put(Block.WHEAT.id, Item.SEEDS.id);
        map.put(Block.BED.id, Item.BED.id);
        map.put(Block.CAKE.id, Item.CAKE.id);
    });

    /**
     * Adds functionality for picking blocks that are not in the hotbar.
     * It also adds pick block functionality to boats, minecarts and paintings.
     * Injected at the head of method_2103, which is the pick block method.
     *
     * @return the selected id
     */
    @ModifyVariable(
            method = "handlePickBlock",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/entity/player/ClientPlayerEntity;",
                    opcode = Opcodes.GETFIELD
            ),
            index = 1
    )
    private int annoyanceFix_pickBlock(int vanillaItemId) {
        if (!Config.config.pickBlockFixesEnabled) {
            return vanillaItemId;
        }

        int itemID = 0;
        // field_790 means "Entity"
        if (this.crosshairTarget.type == HitResultType.ENTITY) {
            itemID = annoyanceFix_getItemIDFromEntity(crosshairTarget.entity);
        }

        // field_789 means "Tile"
        if (this.crosshairTarget.type == HitResultType.BLOCK) {
            int tileDamage = this.world.getBlockMeta(crosshairTarget.blockX, crosshairTarget.blockY, crosshairTarget.blockZ);
            int tileID = this.world.getBlockId(crosshairTarget.blockX, crosshairTarget.blockY, crosshairTarget.blockZ);
            itemID = annoyanceFix_getItemIDFromTileID(tileID, tileDamage);
        }

        if (itemID == 0) {
            // No item found, let vanilla minecraft handle it
            return vanillaItemId;
        }
        // Successfully selected an item, return the new id
        return itemID;
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
        if (tileID == Block.PISTON_HEAD.id) {
            // 0-5 damage = normal piston
            // 8-13 damage = sticky piston
            if (tileDamage < 8) {
                return Block.PISTON.id;
            } else {
                return Block.STICKY_PISTON.id;
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
    private int annoyanceFix_getItemIDFromEntity(Entity entity) {
        if (entity instanceof PaintingEntity) {
            return Item.PAINTING.id;
        }

        if (entity instanceof BoatEntity) {
            return Item.BOAT.id;
        }

        if (entity instanceof MinecartEntity minecart) {
            return switch (minecart.type) {
                case 1 -> Item.CHEST_MINECART.id;
                case 2 -> Item.FURNACE_MINECART.id;
                default -> Item.MINECART.id;
            };
        }

        return 0;
    }
}
