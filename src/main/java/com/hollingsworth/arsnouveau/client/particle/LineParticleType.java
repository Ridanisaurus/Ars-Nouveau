package com.hollingsworth.arsnouveau.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class LineParticleType extends ParticleType<ColoredDynamicTypeData> {
    public LineParticleType() {
        super(false, ColoredDynamicTypeData.DESERIALIZER);
    }

    @Override
    public Codec<ColoredDynamicTypeData> func_230522_e_() {
        return ColoredDynamicTypeData.CODEC;
    }
}
