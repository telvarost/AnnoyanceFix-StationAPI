package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Minecart;
import net.minecraft.entity.Painting;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static java.util.Map.entry;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {

    @Unique
    private final Map<Integer, Integer> pickBlockLookupMap = Map.ofEntries(
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


    @Shadow public HitResult hitResult;
    @Shadow public Level level;
    @Shadow public AbstractClientPlayer player;

    // method_2103 is the pick block method
    @Inject(at = @At("HEAD"), method = "method_2103", cancellable = true)
    public void method_2103(CallbackInfo ci) {
        int itemID = 0;

        if (this.hitResult != null) {
            // field_790 means "Entity"
            if (this.hitResult.type == HitType.field_790) {
                itemID = getItemIDFromEntity(hitResult.field_1989);
            }

            // field_789 means "Tile"
            if (this.hitResult.type == HitType.field_789) {
                int tileDamage = this.level.getTileMeta(hitResult.x, hitResult.y, hitResult.z);
                int tileID = this.level.getTileId(hitResult.x, hitResult.y, hitResult.z);
                itemID = getItemIDFromTileID(tileID, tileDamage);
            }

            if (itemID == 0) {
                // No item found, let vanilla minecraft handle it
                return;
            }

            this.player.inventory.setSelectedItemWithID(itemID, false);
            // Successfully selected an item, don't call original method anymore
            ci.cancel();
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
    private int getItemIDFromTileID(int tileID, int tileDamage) {
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
    @Unique
    private int getItemIDFromEntity(EntityBase entity) {
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
