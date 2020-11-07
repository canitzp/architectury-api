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

package me.shedaniel.architectury.registry;

import me.shedaniel.architectury.ArchitecturyPopulator;
import me.shedaniel.architectury.Populatable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@Environment(EnvType.CLIENT)
public final class RenderTypes {
    private RenderTypes() {}
    
    @Populatable
    private static final Impl IMPL = null;
    
    public static void register(RenderType type, Block... blocks) {
        IMPL.register(type, blocks);
    }
    
    public static void register(RenderType type, Fluid... fluids) {
        IMPL.register(type, fluids);
    }
    
    public interface Impl {
        void register(RenderType type, Block... blocks);
        
        void register(RenderType type, Fluid... fluids);
    }
    
    static {
        ArchitecturyPopulator.populate(RenderTypes.class);
    }
}
