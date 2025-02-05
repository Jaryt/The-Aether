package com.gildedgames.aether.common.world.gen.feature;

import com.gildedgames.aether.common.block.state.properties.AetherBlockStateProperties;
import com.gildedgames.aether.common.registry.AetherBlocks;
import com.gildedgames.aether.common.registry.AetherTags;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class QuicksoilFeature extends Feature<NoFeatureConfig> {

    public QuicksoilFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        boolean doesProtrude = (
                (reader.getBlockState(pos.west(3)).isAir() ||
                        reader.getBlockState(pos.north(3)).isAir() ||
                        reader.getBlockState(pos.south(3)).isAir() ||
                        reader.getBlockState(pos.east(3)).isAir()) &&
                (reader.getBlockState(pos).is(AetherTags.Blocks.HOLYSTONE) ||
                        reader.getBlockState(pos).getBlock() == AetherBlocks.AETHER_DIRT.get()));
        if (doesProtrude) {
            for(int x = pos.getX() - 3; x < pos.getX() + 4; x++) {
                for(int z = pos.getZ() - 3; z < pos.getZ() + 4; z++) {
                    BlockPos newPos = new BlockPos(x, pos.getY(), z);

                    if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < 12) {
                        reader.setBlock(newPos, AetherBlocks.QUICKSOIL.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true), 0);
                    }
                }

            }
        }
        return false;
    }
}
