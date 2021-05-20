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

package me.shedaniel.architectury.event.events;

import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResultHolder;

public interface ChatEvent {
    /**
     * @see Server#process(ServerPlayer, String, Component)
     */
    Event<Server> SERVER = EventFactory.createInteractionResultHolder();
    
    interface Server {
        /**
         * Invoked when the server receives a message from a client.
         * Equal to the Forge {@code ServerChatEvent} event.
         * 
         * @param player The player who has sent the message.
         * @param message The raw message itself.
         * @param component The message as component.
         * @return Returning {@link InteractionResultHolder#fail(Object)} leads to the cancellation of the message receiving.
         * Every other {@link InteractionResultHolder#getObject()} is used as the component to process further.
         */
        InteractionResultHolder<Component> process(ServerPlayer player, String message, Component component);
    }
}
