package net.glasslauncher.annoyancefix.mixin;

import net.glasslauncher.annoyancefix.AnnoyanceFixConfig;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = Boat.class)
public abstract class BoatMixin extends EntityBase {
    @Shadow public int field_338 = 0;
    @Shadow public int field_339 = 0;
    @Shadow public int field_340 = 1;
    @Shadow private int field_341;
    @Shadow private double field_342;
    @Shadow private double field_343;
    @Shadow private double field_344;
    @Shadow private double field_345;
    @Shadow private double field_346;

    public BoatMixin(Level level) {
        super(level);
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void damage(EntityBase arg, int i, CallbackInfoReturnable<Boolean> cir) {
        if (AnnoyanceFixConfig.enableBoatFixes) {
            if (!this.level.isServerSide && !this.removed) {
                this.field_340 = -this.field_340;
                this.field_339 = 10;
                this.field_338 += i * 10;
                this.method_1336();
                if (this.field_338 > 40) {
                    if (this.passenger != null) {
                        this.passenger.startRiding(this);
                    }

                    this.dropItem(ItemBase.boat.id, 1, 0.0F);

                    this.remove();
                }

                cir.setReturnValue(true);
            } else {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        if (AnnoyanceFixConfig.enableBoatFixes) {
            super.tick();
            if (this.field_339 > 0) {
                --this.field_339;
            }

            if (this.field_338 > 0) {
                --this.field_338;
            }

            this.prevX = this.x;
            this.prevY = this.y;
            this.prevZ = this.z;
            byte var1 = 5;
            double var2 = 0.0;

            for (int var4 = 0; var4 < var1; ++var4) {
                double var5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (var4 + 0) / (double) var1 - 0.125;
                double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (var4 + 1) / (double) var1 - 0.125;
                Box var9 = Box.createButWasteMemory(this.boundingBox.minX, var5, this.boundingBox.minZ, this.boundingBox.maxX, var7, this.boundingBox.maxZ);
                if (this.level.method_207(var9, Material.WATER)) {
                    var2 += 1.0 / (double) var1;
                }
            }

            double var6;
            double var8;
            double var10;
            double var21;
            if (this.level.isServerSide) {
                if (this.field_341 > 0) {
                    var21 = this.x + (this.field_342 - this.x) / (double) this.field_341;
                    var6 = this.y + (this.field_343 - this.y) / (double) this.field_341;
                    var8 = this.z + (this.field_344 - this.z) / (double) this.field_341;

                    for (var10 = this.field_345 - (double) this.yaw; var10 < -180.0; var10 += 360.0) {
                    }

                    while (var10 >= 180.0) {
                        var10 -= 360.0;
                    }

                    this.yaw = (float) ((double) this.yaw + var10 / (double) this.field_341);
                    this.pitch = (float) ((double) this.pitch + (this.field_346 - (double) this.pitch) / (double) this.field_341);
                    --this.field_341;
                    this.setPosition(var21, var6, var8);
                    this.setRotation(this.yaw, this.pitch);
                } else {
                    var21 = this.x + this.velocityX;
                    var6 = this.y + this.velocityY;
                    var8 = this.z + this.velocityZ;
                    this.setPosition(var21, var6, var8);
                    if (this.onGround) {
                        this.velocityX *= 0.5;
                        this.velocityY *= 0.5;
                        this.velocityZ *= 0.5;
                    }

                    this.velocityX *= 0.9900000095367432;
                    this.velocityY *= 0.949999988079071;
                    this.velocityZ *= 0.9900000095367432;
                }

            } else {
                if (var2 < 1.0) {
                    var21 = var2 * 2.0 - 1.0;
                    this.velocityY += 0.03999999910593033 * var21;
                } else {
                    if (this.velocityY < 0.0) {
                        this.velocityY /= 2.0;
                    }

                    this.velocityY += 0.007000000216066837;
                }

                if (this.passenger != null) {
                    this.velocityX += this.passenger.velocityX * 0.2;
                    this.velocityZ += this.passenger.velocityZ * 0.2;
                }

                var21 = 0.4;
                if (this.velocityX < -var21) {
                    this.velocityX = -var21;
                }

                if (this.velocityX > var21) {
                    this.velocityX = var21;
                }

                if (this.velocityZ < -var21) {
                    this.velocityZ = -var21;
                }

                if (this.velocityZ > var21) {
                    this.velocityZ = var21;
                }

                if (this.onGround) {
                    this.velocityX *= 0.5;
                    this.velocityY *= 0.5;
                    this.velocityZ *= 0.5;
                }

                this.move(this.velocityX, this.velocityY, this.velocityZ);
                var6 = Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
                if (var6 > 0.15) {
                    var8 = Math.cos((double) this.yaw * Math.PI / 180.0);
                    var10 = Math.sin((double) this.yaw * Math.PI / 180.0);

                    for (int var12 = 0; (double) var12 < 1.0 + var6 * 60.0; ++var12) {
                        double var13 = (double) (this.rand.nextFloat() * 2.0F - 1.0F);
                        double var15 = (double) (this.rand.nextInt(2) * 2 - 1) * 0.7;
                        double var17;
                        double var19;
                        if (this.rand.nextBoolean()) {
                            var17 = this.x - var8 * var13 * 0.8 + var10 * var15;
                            var19 = this.z - var10 * var13 * 0.8 - var8 * var15;
                            this.level.addParticle("splash", var17, this.y - 0.125, var19, this.velocityX, this.velocityY, this.velocityZ);
                        } else {
                            var17 = this.x + var8 + var10 * var13 * 0.7;
                            var19 = this.z + var10 - var8 * var13 * 0.7;
                            this.level.addParticle("splash", var17, this.y - 0.125, var19, this.velocityX, this.velocityY, this.velocityZ);
                        }
                    }
                }

//            if (this.field_1624 && var6 > 0.15) {
//                if (!this.level.isServerSide) {
//                    this.remove();
//
//                    int var22;
//                    for (var22 = 0; var22 < 3; ++var22) {
//                        this.dropItem(BlockBase.WOOD.id, 1, 0.0F);
//                    }
//
//                    for (var22 = 0; var22 < 2; ++var22) {
//                        this.dropItem(ItemBase.stick.id, 1, 0.0F);
//                    }
//                }
//            } else {
                this.velocityX *= 0.9900000095367432;
                this.velocityY *= 0.949999988079071;
                this.velocityZ *= 0.9900000095367432;
//            }

                this.pitch = 0.0F;
                var8 = (double) this.yaw;
                var10 = this.prevX - this.x;
                double var23 = this.prevZ - this.z;
                if (var10 * var10 + var23 * var23 > 0.001) {
                    var8 = (double) ((float) (Math.atan2(var23, var10) * 180.0 / Math.PI));
                }

                double var14;
                for (var14 = var8 - (double) this.yaw; var14 >= 180.0; var14 -= 360.0) {
                }

                while (var14 < -180.0) {
                    var14 += 360.0;
                }

                if (var14 > 20.0) {
                    var14 = 20.0;
                }

                if (var14 < -20.0) {
                    var14 = -20.0;
                }

                this.yaw = (float) ((double) this.yaw + var14);
                this.setRotation(this.yaw, this.pitch);
                List var16 = this.level.getEntities(this, this.boundingBox.expand(0.20000000298023224, 0.0, 0.20000000298023224));
                int var24;
                if (var16 != null && var16.size() > 0) {
                    for (var24 = 0; var24 < var16.size(); ++var24) {
                        EntityBase var18 = (EntityBase) var16.get(var24);
                        if (var18 != this.passenger && var18.method_1380() && var18 instanceof Boat) {
                            var18.method_1353(this);
                        }
                    }
                }

                for (var24 = 0; var24 < 4; ++var24) {
                    int var25 = MathHelper.floor(this.x + ((double) (var24 % 2) - 0.5) * 0.8);
                    int var26 = MathHelper.floor(this.y);
                    int var20 = MathHelper.floor(this.z + ((double) (var24 / 2) - 0.5) * 0.8);
                    if (this.level.getTileId(var25, var26, var20) == BlockBase.SNOW.id) {
                        this.level.setTile(var25, var26, var20, 0);
                    }
                }

                if (this.passenger != null && this.passenger.removed) {
                    this.passenger = null;
                }
            }

            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    public void interact(PlayerBase arg, CallbackInfoReturnable<Boolean> cir) {
        if (AnnoyanceFixConfig.enableBoatFixes) {
            if (this.passenger != null && this.passenger instanceof PlayerBase && this.passenger != arg) {
                cir.setReturnValue(true);
            } else {
                if (!this.level.isServerSide) {
                    arg.startRiding(this);
                    // If player is not riding anything after interacting with the boat, it must have unmounted
                    if (arg.vehicle == null) {
                        // Compensate for floating point errors
                        arg.setPosition(arg.x, arg.y + 0.01f, arg.z);
                    }
                }

                cir.setReturnValue(true);
            }
        }
    }
}

