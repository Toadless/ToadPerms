package uk.toadl3ss.Toadperms.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.Main;

public class UUID implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equals("uuid")) {
            if (!(sender instanceof Player)) {
                System.out.println("Console");
                return true;
            }
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "Your UUID:" + " " + player.getUniqueId());
            return true;
        }
        return true;
    }
}