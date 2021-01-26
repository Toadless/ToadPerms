package uk.toadl3ss.Toadperms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import uk.toadl3ss.Toadperms.Blocks.breakBlock;
import uk.toadl3ss.Toadperms.Blocks.placeBlock;
import uk.toadl3ss.Toadperms.Commands.*;
import uk.toadl3ss.Toadperms.Events.*;
import uk.toadl3ss.Toadperms.Permissions.Permissions;
import uk.toadl3ss.Toadperms.Utils.Vanity;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Utils.openScoreboard;

public class Main extends JavaPlugin {
    public static Main plugin;
    public void onEnable() {
        plugin = this;
        Vanity.printVainity();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        database.main(this.getConfig().getString("database"));
        getServer().getPluginManager().registerEvents(new breakBlock(), this);
        getServer().getPluginManager().registerEvents(new placeBlock(), this);
        getServer().getPluginManager().registerEvents(new Permissions(), this);
        getServer().getPluginManager().registerEvents(new RankGUISelect(), this);
        getServer().getPluginManager().registerEvents(new ConfirmGUISelect(), this);
        getServer().getPluginManager().registerEvents(new onInventoryClose(), this);
        getServer().getPluginManager().registerEvents(new GrantsGUISelect(), this);
        getServer().getPluginManager().registerEvents(new GrantConfirmGUISelect(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getCommand("rank").setExecutor(new Rank());
        getCommand("grant").setExecutor(new Grant());
        getCommand("grants").setExecutor(new Grants());
        getCommand("permsreload").setExecutor(new Reload());
        getCommand("uuid").setExecutor(new UUID());
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                openScoreboard.openScoreboard(online);
            }
        }
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " " + "[Toadperms]" + " " + "Loaded!!");
    }
    public void onDisable() {
        Permissions.playerPermissions.clear();
    }
}