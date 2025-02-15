package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
abstract class BoatMixin extends Entity {

    public BoatMixin(World level) {
        super(level);
    }

    @ModifyConstant(
            method = "damage",
            constant = @Constant(intValue = 3)
    )
    private int annoyanceFix_skipPlanksDrop(int constant) {
        if (Config.config.boatDropFixesEnabled) {
            return 0;
        } else {
            return 3;
        }
    }

    @ModifyConstant(
            method = "damage",
            constant = @Constant(intValue = 2)
    )
    private int annoyanceFix_skipSticksDrop(int constant) {
        if (Config.config.boatDropFixesEnabled) {
            return 0;
        } else {
            return 2;
        }
    }

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/vehicle/BoatEntity;markDead()V"
            )
    )
    private void annoyanceFix_dropBoat(Entity attacker, int damage, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.boatDropFixesEnabled) {
            dropItem(Item.BOAT.id, 1, 0);
        }
    }

    @ModifyConstant(
            method = "tick",
            constant = @Constant(intValue = 3)
    )
    private int annoyanceFix_skipPlanksDropOnCollision(int constant) {
        if (0 < Config.config.boatCollisionBehavior.ordinal()) {
            return 0;
        } else {
            return 3;
        }
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/vehicle/BoatEntity;dropItem(IIF)Lnet/minecraft/entity/ItemEntity;",
                    ordinal = 1
            )
    )
    private ItemEntity annoyanceFix_skipSticksDropOnCollision(BoatEntity instance, int i, int j, float f, Operation<ItemEntity> original) {
        if (0 < Config.config.boatCollisionBehavior.ordinal()) {
            return null;
        } else {
            return original.call(instance, i, j, f);
        }
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/vehicle/BoatEntity;markDead()V"
            )
    )
    private void annoyanceFix_dropBoatOnCollision(CallbackInfo ci) {
        if (0 < Config.config.boatCollisionBehavior.ordinal()) {
            dropItem(Item.BOAT.id, 1, 0);
        }
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/vehicle/BoatEntity;horizontalCollision:Z",
                    opcode = Opcodes.GETFIELD
            )
    )
    private boolean annoyanceFix_stopBoatBreaking(BoatEntity instance) {
        if (1 < Config.config.boatCollisionBehavior.ordinal()) {
            return false;
        } else {
            return this.horizontalCollision;
        }
    }

    @Inject(
            method = "interact",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;setVehicle(Lnet/minecraft/entity/Entity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void annoyanceFix_compensateForFloatingPointErrors(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        // If player is not riding anything after interacting with the boat, it must have unmounted
        if (player.vehicle == null) {
            // Compensate for floating point errors
            player.setPosition(player.x, player.y + 0.01, player.z);
        }
    }
}

