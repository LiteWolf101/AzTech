package com.litewolf101.aztech.proxy;

import net.minecraft.world.World;

public interface IProxy {
    World getClientWorld();

    void initBlockColors();
}
