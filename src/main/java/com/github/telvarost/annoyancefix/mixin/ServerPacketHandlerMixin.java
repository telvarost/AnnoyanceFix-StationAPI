package com.github.telvarost.annoyancefix.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.packet.login.LoginRequest0x1Packet;
import net.minecraft.packet.play.ChatMessage0x3Packet;
import net.minecraft.packet.play.SpawnPosition0x6S2CPacket;
import net.minecraft.packet.play.TimeUpdate0x4S2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.ClientConnection;
import net.minecraft.server.network.ServerPacketHandler;
import net.minecraft.server.network.ServerPlayerPacketHandler;
import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Logger;

@Environment(EnvType.SERVER)
@Mixin(ServerPacketHandler.class)
public abstract class ServerPacketHandlerMixin {


    @Shadow private MinecraftServer server;

    @Shadow public abstract String getUsernameAndIp();

    @Shadow public static Logger logger;

    @Shadow public ClientConnection playNetworkHandler;

    @Shadow public boolean closed;

    @Inject(
            method = "complete",
            at = @At("HEAD"),
            cancellable = true
    )
    public void complete(LoginRequest0x1Packet arg, CallbackInfo ci) {
        ServerPlayer var2 = this.server.serverPlayerConnectionManager.connectPlayer(((ServerPacketHandler) (Object)this), arg.username);
        if (var2 != null) {
            this.server.serverPlayerConnectionManager.method_566(var2);
            var2.setLevel(this.server.getLevel(var2.dimensionId));
            logger.info(this.getUsernameAndIp() + " logged in with entity id " + var2.entityId + " at (" + var2.x + ", " + var2.y + ", " + var2.z + ")");
            ServerLevel var3 = this.server.getLevel(var2.dimensionId);
            Vec3i var4 = var3.getSpawnPosition();
            ServerPlayerPacketHandler var5 = new ServerPlayerPacketHandler(this.server, this.playNetworkHandler, var2);
            var5.send(new LoginRequest0x1Packet("", var2.entityId, var3.getSeed(), (byte)var3.dimension.id));
            var5.send(new SpawnPosition0x6S2CPacket(var4.x, var4.y, var4.z));

            var2.startRiding(var2.vehicle); // <-- Add join vehicle here

            this.server.serverPlayerConnectionManager.sendPlayerTime(var2, var3);
            this.server.serverPlayerConnectionManager.sendToAll(new ChatMessage0x3Packet("§e" + var2.name + " joined the game."));
            this.server.serverPlayerConnectionManager.method_569(var2);
            var5.method_832(var2.x, var2.y, var2.z, var2.yaw, var2.pitch);
            this.server.pendingConnectionManager.method_38(var5);
            var5.send(new TimeUpdate0x4S2CPacket(var3.getLevelTime()));
            var2.method_317();
        }

        this.closed = true;
    }
}
