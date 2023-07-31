package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.glasslauncher.annoyancefix.PickBlockMapper.*;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {

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
}
