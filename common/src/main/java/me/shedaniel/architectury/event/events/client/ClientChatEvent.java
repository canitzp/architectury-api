/*
 * This file is part of architectury.
 * Copyright (C) 2020, 2021 architectury
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.shedaniel.architectury.event.events.client;

import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResultHolder;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public interface ClientChatEvent {
    /**
     * Invoked when client tries to send a message, equivalent to forge's {@code ClientChatEvent}.
     */
    Event<Client> CLIENT = EventFactory.createInteractionResultHolder();
    /**
     * Invoked when client receives a message, equivalent to forge's {@code ClientChatReceivedEvent}.
     */
    Event<ClientReceived> CLIENT_RECEIVED = EventFactory.createInteractionResultHolder();
    
    @Environment(EnvType.CLIENT)
    interface Client {
        InteractionResultHolder<String> process(String message);
    }
    
    @Environment(EnvType.CLIENT)
    interface ClientReceived {
        InteractionResultHolder<Component> process(ChatType type, Component message, @Nullable UUID sender);
    }
}
