package com.github.pelotrio.realfakeplayers;

import com.github.pelotrio.realfakeplayers.commands.RealFakePlayerCommands;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = Realfakeplayers.MOD_ID,
        name = Realfakeplayers.MOD_NAME,
        version = Realfakeplayers.VERSION
)
public class Realfakeplayers {

    public static final String MOD_ID = "realfakeplayers";
    public static final String MOD_NAME = "RealFakePlayers";
    public static final String VERSION = "1.0-SNAPSHOT";


    //Mod Instance
    @Mod.Instance(MOD_ID)
    public static Realfakeplayers INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    //Initialize player commands
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new RealFakePlayerCommands());
    }

}
