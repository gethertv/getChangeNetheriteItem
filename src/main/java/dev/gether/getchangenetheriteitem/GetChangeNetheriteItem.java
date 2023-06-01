package dev.gether.getchangenetheriteitem;

import dev.gether.getchangenetheriteitem.cmd.GetChangeNetheriteCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class GetChangeNetheriteItem extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getServer().getCommandMap().register("getchangenetherite", new GetChangeNetheriteCommand("getchangenetherite", this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
