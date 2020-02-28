package com.litewolf101.aztech.init;

import com.litewolf101.aztech.AzTech;
import com.litewolf101.aztech.blocks.tileEntity.TEObjectiveBlock;
import com.litewolf101.aztech.blocks.tileEntity.TESlaughterhouseBlock;
import com.litewolf101.aztech.utils.ModUtils;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AzTech.MODID)
public class ModTileEntityTypes {
    public static final TileEntityType<TEObjectiveBlock> OBJECTIVE_BLOCK = ModUtils._null();
    public static final TileEntityType<TESlaughterhouseBlock> SLAUGHTERHOUSE_DETECTOR = ModUtils._null();
}
