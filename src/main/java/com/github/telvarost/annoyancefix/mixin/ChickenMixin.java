package com.github.telvarost.annoyancefix.mixin;

import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.animal.Chicken;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public class ChickenMixin extends AnimalBase {
    @Shadow
    int field_2165;

    public ChickenMixin(Level arg) {
        super(arg);
        this.texture = "/mob/chicken.png";
        this.setSize(0.3F, 0.4F);
        this.health = 4;
        this.field_2165 = this.rand.nextInt(6000) + 6000;
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
