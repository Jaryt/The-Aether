package com.gildedgames.aether.client.renderer.entity;

import com.gildedgames.aether.common.entity.block.FloatingBlockEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class FloatingBlockRenderer extends EntityRenderer<FloatingBlockEntity> {

	public FloatingBlockRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		this.shadowRadius = 0.5F;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(FloatingBlockEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		BlockState blockstate = entityIn.getBlockState();
	      if (blockstate.getRenderShape() == BlockRenderType.MODEL) {
	         World world = entityIn.getWorldObj();
	         if (blockstate != world.getBlockState(new BlockPos(entityIn.blockPosition())) && blockstate.getRenderShape() != BlockRenderType.INVISIBLE) {
	            matrixStackIn.pushPose();
	            BlockPos blockpos = new BlockPos(entityIn.getX(), entityIn.getBoundingBox().maxY, entityIn.getZ());
	            matrixStackIn.translate(-0.5, 0.0, -0.5);
	            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
	            for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.chunkBufferLayers()) {
	               if (RenderTypeLookup.canRenderInLayer(blockstate, type)) {
	                  net.minecraftforge.client.ForgeHooksClient.setRenderLayer(type);
	                  blockrendererdispatcher.getModelRenderer().tesselateBlock(world, blockrendererdispatcher.getBlockModel(blockstate), blockstate, blockpos, matrixStackIn, bufferIn.getBuffer(type), false, new Random(), blockstate.getSeed(entityIn.getOrigin()), OverlayTexture.NO_OVERLAY);
	               }
	            }
	            net.minecraftforge.client.ForgeHooksClient.setRenderLayer(null);
	            matrixStackIn.popPose();
	            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	         }
	      }
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResourceLocation getTextureLocation(FloatingBlockEntity entity) {
		return AtlasTexture.LOCATION_BLOCKS;
	}

}
