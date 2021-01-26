package uk.toadl3ss.Toadperms.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.Events.GrantsGUISelect;
import uk.toadl3ss.Toadperms.GUIS.GrantsGUI;
import uk.toadl3ss.Toadperms.Main;

public class Reload implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equals("permsreload")) {
            if (!(sender instanceof Player)) {
                Main.plugin.reloadConfig();
                System.out.println("Reloaded perms config.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("tp.reload")) {
                player.sendMessage(ChatColor.RED + "You dont have permission to do that!");
                return true;
            }
            Main.plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "Reloaded perms config!");
            return true;
        }
        return true;
    }
}