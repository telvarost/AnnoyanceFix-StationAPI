package com.github.telvarost.annoyancefix.mixin;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChickenEntity.class)
public class ChickenMixin extends AnimalEntity {
    @Shadow
    int eggLayTime;

    public ChickenMixin(World arg) {
        super(arg);
        this.texture = "/mob/chicken.png";
        this.setBoundingBoxSpacing(0.3F, 0.4F);
        this.health = 4;
        this.eggLayTime = this.random.nextInt(6000) + 6000;
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(floatValue = 0.3F)
    )
    public float ModifyWidth(float constant) {
        return 0.4F;
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(floatValue = 0.4F)
    )
    public float ModifyHeight(float constant) {
        return 0.7F;
    }
}
