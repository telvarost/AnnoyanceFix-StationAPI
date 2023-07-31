package net.glasslauncher.annoyancefix;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Minecart;
import net.minecraft.entity.Painting;
import net.minecraft.item.ItemBase;

import java.util.Map;

import static java.util.Map.entry;

public class PickBlockMapper {
    private static final Map<Integer, Integer> pickBlockLookupMap = Map.ofEntries(
            entry(BlockBase.REDSTONE_DUST.id, ItemBase.redstoneDust.id),
            entry(BlockBase.REDSTONE_REPEATER.id, ItemBase.redstoneRepeater.id),
            entry(BlockBase.REDSTONE_REPEATER_LIT.id, ItemBase.redstoneRepeater.id),
            entry(BlockBase.WOOD_DOOR.id, ItemBase.woodDoor.id),
            entry(BlockBase.IRON_DOOR.id, ItemBase.ironDoor.id),
            entry(BlockBase.STANDING_SIGN.id, ItemBase.sign.id),
            entry(BlockBase.WALL_SIGN.id, ItemBase.sign.id),
            entry(BlockBase.CROPS.id, ItemBase.seeds.id),
            entry(BlockBase.BED.id, ItemBase.bed.id),
            entry(BlockBase.CAKE.id, ItemBase.cake.id)
    );

    /**
     * Returns a corresponding item ID for a given tile.
     * For example, when looking at crops, it should return the seeds item.
     *
     * @param tileID the ID of the tile
     * @return the corresponding item ID
     */
    public static int getItemIDFromTileID(int tileID, int tileDamage) {
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

        if (!pickBlockLookupMap.containsKey(tileID)) {
            return 0;
        }
        return pickBlockLookupMap.get(tileID);
    }

    /**
     * Returns a corresponding item ID for a given entity.
     * For example, when looking at a boat, it should return a boat.
     *
     * @param entity the entity to check
     * @return the corresponding item ID
     */
    public static int getItemIDFromEntity(EntityBase entity) {
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
