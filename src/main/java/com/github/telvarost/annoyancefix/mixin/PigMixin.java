package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntity.class)
public abstract class PigMixin extends AnimalEntity {

    @Shadow public abstract boolean isSaddled();

    public PigMixin(World arg) {
        super(arg);
        this.texture = "/mob/pig.png";
        this.setBoundingBoxSpacing(0.9F, 0.9F);
    }

    @Inject(
            method = "getDroppedItemId",
            at = @At("HEAD"),
            cancellable = true
    )
    private void annoyanceFix_getMobDrops(CallbackInfoReturnable<Integer> cir) {
        if (  (Config.config.pigFixesEnabled)
           && (this.isSaddled())
        ) {
            this.dropItem(Item.SADDLE.id, 1);
        }
    }
}
