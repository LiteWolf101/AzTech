package com.litewolf101.aztech.proxy;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this code on the Client!");
    }

    public void initBlockColors () {}
}
