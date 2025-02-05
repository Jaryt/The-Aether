package com.gildedgames.aether.core.capability.interfaces;

import com.gildedgames.aether.common.entity.block.ParachuteEntity;
import com.gildedgames.aether.core.api.registers.ParachuteType;
import com.gildedgames.aether.core.capability.AetherCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public interface IAetherPlayer extends INBTSerializable<CompoundNBT>
{
	PlayerEntity getPlayer();

	static LazyOptional<IAetherPlayer> get(PlayerEntity player) {
		return player.getCapability(AetherCapabilities.AETHER_PLAYER_CAPABILITY);
	}

	void onUpdate();

	void copyFrom(IAetherPlayer other);

	void setParachute(ParachuteType parachuteType);
	ParachuteEntity getParachute();

	void setJumping(boolean isJumping);
	boolean isJumping();
}
