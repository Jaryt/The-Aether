package com.gildedgames.aether.common.block.natural;

import java.util.Random;

import com.gildedgames.aether.common.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import net.minecraft.block.AbstractBlock;

public class BerryBushStemBlock extends AetherBushBlock implements IGrowable
{
	protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

	public BerryBushStemBlock(AbstractBlock.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) {
			return;
		}

		if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(60) == 0) {
			this.performBonemeal(worldIn, random, pos, state);
		}
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return rand.nextFloat() < 0.45F;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		worldIn.setBlockAndUpdate(pos, AetherBlocks.BERRY_BUSH.get().defaultBlockState());
	}
}
