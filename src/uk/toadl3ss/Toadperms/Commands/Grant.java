package uk.toadl3ss.Toadperms.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.GUIS.RankGUI;

import java.util.HashMap;
import java.util.UUID;

public class Grant implements CommandExecutor {
    public static HashMap<UUID, Player> grantHashMap = new HashMap<>();
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.AQUA + " " + "You need to be a player to do that. Try /rank <user> <rank>");
            return true;
        }
        if (cmd.getName().equals("grant")) {
            if (!player.hasPermission("tp.grant")) {
                player.sendMessage(ChatColor.RED + "You dont have permission to do that!");
                return true;
            }
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "You need to specify a user to do that on!");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "You need to specify a valid user!");
                return true;
            }
            RankGUI gui = new RankGUI(target);
            player.openInventory(gui.getInventory());
            grantHashMap.put(player.getUniqueId(), target);
            return true;
            //player.sendMessage(ChatColor.GREEN + " " + "Successfully opened the grant gui for:" + " " + target.getName() + "!");
        }
        return true;
    }
}
