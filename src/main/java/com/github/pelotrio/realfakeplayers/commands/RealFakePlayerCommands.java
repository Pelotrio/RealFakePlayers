package com.github.pelotrio.realfakeplayers.commands;

import com.github.pelotrio.realfakeplayers.entity.RealFakePlayerMP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class RealFakePlayerCommands extends CommandBase {

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "retard";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = ((EntityPlayer) sender);
        RealFakePlayerMP.createFakePlayer(args[0], server, player.dimension, player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
    }
}
