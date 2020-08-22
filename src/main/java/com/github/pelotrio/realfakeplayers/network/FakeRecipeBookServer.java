package com.github.pelotrio.realfakeplayers.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.RecipeBookServer;

public class FakeRecipeBookServer extends RecipeBookServer {

    @Override
    public void init(EntityPlayerMP player) {
        //NO OP
    }
}
