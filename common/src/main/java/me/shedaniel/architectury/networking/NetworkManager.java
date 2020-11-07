/*
 * Copyright 2020 shedaniel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.shedaniel.architectury.networking;

import me.shedaniel.architectury.ArchitecturyPopulator;
import me.shedaniel.architectury.Populatable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public final class NetworkManager {
    @Populatable
    private static final Impl IMPL = null;
    
    public static void registerReceiver(Side side, ResourceLocation id, NetworkReceiver receiver) {
        IMPL.registerReceiver(side, id, receiver);
    }
    
    public static Packet<?> toPacket(Side side, ResourceLocation id, FriendlyByteBuf buf) {
        return IMPL.toPacket(side, id, buf);
    }
    
    public static void sendToPlayer(ServerPlayer player, ResourceLocation id, FriendlyByteBuf buf) {
        player.connection.send(toPacket(serverToClient(), id, buf));
    }
    
    public static void sendToPlayers(Iterable<ServerPlayer> players, ResourceLocation id, FriendlyByteBuf buf) {
        Packet<?> packet = toPacket(serverToClient(), id, buf);
        for (ServerPlayer player : players) {
            player.connection.send(packet);
        }
    }
    
    @Environment(EnvType.CLIENT)
    public static void sendToServer(ResourceLocation id, FriendlyByteBuf buf) {
        Minecraft.getInstance().getConnection().send(toPacket(clientToServer(), id, buf));
    }
    
    @Environment(EnvType.CLIENT)
    public static boolean canServerReceive(ResourceLocation id) {
        return IMPL.canServerReceive(id);
    }
    
    public static boolean canPlayerReceive(ServerPlayer player, ResourceLocation id) {
        return IMPL.canPlayerReceive(player, id);
    }
    
    public interface Impl {
        void registerReceiver(Side side, ResourceLocation id, NetworkReceiver receiver);
        
        Packet<?> toPacket(Side side, ResourceLocation id, FriendlyByteBuf buf);
        
        @Environment(EnvType.CLIENT)
        boolean canServerReceive(ResourceLocation id);
        
        boolean canPlayerReceive(ServerPlayer player, ResourceLocation id);
    }
    
    @FunctionalInterface
    public interface NetworkReceiver {
        void receive(FriendlyByteBuf buf, PacketContext context);
    }
    
    public interface PacketContext {
        Player getPlayer();
        
        void queue(Runnable runnable);
        
        EnvType getEnv();
    }
    
    public static Side s2c() {
        return Side.S2C;
    }
    
    public static Side c2s() {
        return Side.C2S;
    }
    
    public static Side serverToClient() {
        return Side.S2C;
    }
    
    public static Side clientToServer() {
        return Side.C2S;
    }
    
    public enum Side {
        S2C,
        C2S
    }
    
    static {
        ArchitecturyPopulator.populate(NetworkManager.class);
    }
}