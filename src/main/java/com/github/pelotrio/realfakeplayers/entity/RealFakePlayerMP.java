package com.github.pelotrio.realfakeplayers.entity;

import com.github.pelotrio.realfakeplayers.network.FakeRecipeBookServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.stats.RecipeBookServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldServer;

@SuppressWarnings("EntityConstructor")
public class RealFakePlayerMP extends EntityPlayerMP {

    private double lastReportedPosX;
    private double lastReportedPosY;
    private double lastReportedPosZ;

    private RealFakePlayerMP(MinecraftServer server, WorldServer worldIn, GameProfile profile, PlayerInteractionManager interactionManagerIn) {
        super(server, worldIn, profile, interactionManagerIn);
    }

    public static RealFakePlayerMP createFakePlayer(String name, MinecraftServer server, int dimension, double x, double y, double z, float yaw, float pitch ){

        WorldServer worldIn = server.getWorld(dimension);

        GameProfile profile = server.getPlayerProfileCache().getGameProfileForUsername(name);
        PlayerInteractionManager interactionManagerIn = new PlayerInteractionManager(worldIn);

        RealFakePlayerMP fakePlayer = new RealFakePlayerMP(server, worldIn, profile, interactionManagerIn);


        NetworkManager manager = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
        server.getPlayerList().initializeConnectionToPlayer(manager, fakePlayer, new NetHandlerPlayServer(server, manager, fakePlayer));

        fakePlayer.setHealth(20.0F);
        fakePlayer.connection.setPlayerLocation(x, y, z, 0, 0);
        fakePlayer.setVelocity(0, 0, 0);
        fakePlayer.isDead = false;
        fakePlayer.stepHeight = 0.6F;
        interactionManagerIn.setGameType(GameType.SURVIVAL);
        server.getPlayerList().sendPacketToAllPlayersInDimension(new SPacketEntityHeadLook(fakePlayer, (byte)(fakePlayer.rotationYawHead * 256 / 360) ),fakePlayer.dimension);
        server.getPlayerList().sendPacketToAllPlayersInDimension(new SPacketEntityTeleport(fakePlayer),fakePlayer.dimension);
        server.getPlayerList().serverUpdateMovingPlayer(fakePlayer);
        fakePlayer.dataManager.set(PLAYER_MODEL_FLAG, (byte) 0x7f);
        return  fakePlayer;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.onUpdateEntity();
        this.playerMoved();
    }

    private void playerMoved() {
        if (posX != lastReportedPosX || posY != lastReportedPosY || posZ != lastReportedPosZ) {
            server.getPlayerList().serverUpdateMovingPlayer(this);
            lastReportedPosX = posX;
            lastReportedPosY = posY;
            lastReportedPosZ = posZ;
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        getServer().getPlayerList().playerLoggedOut(this);
    }

    @Override
    public RecipeBookServer getRecipeBook() {
        return new FakeRecipeBookServer();
    }
}
