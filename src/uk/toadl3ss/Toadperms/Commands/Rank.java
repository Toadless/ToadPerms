package uk.toadl3ss.Toadperms.Commands;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Main;
import uk.toadl3ss.Toadperms.Utils.openScoreboard;
import uk.toadl3ss.Toadperms.Utils.setChatPrefix;
import uk.toadl3ss.Toadperms.Utils.setRank;

public class Rank implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                System.out.println(ChatColor.RED + "You need to specify a user to do that on!");
                return true;
            }
            if (args.length < 2) {
                System.out.println(ChatColor.RED + "You need to specify a rank to give" + " " + args[0]);
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                System.out.println(ChatColor.RED + "You need to specify a valid user!");
                return true;
            }
            try {
                DBObject query = new BasicDBObject("uuid", target.getUniqueId());

                DBObject update = new BasicDBObject("rank", args[1]);
                update.put("uuid", target.getUniqueId());

                DBCursor cursor = database.user.find(query);
                if (cursor.one() == null) {
                    return true;
                }

                database.user.update(query, update);
                openScoreboard.openScoreboard(target);
                setRank.setupPermissions(target, cursor.one().get("rank").toString());
                setChatPrefix.setChatPrefix(cursor.one().get("rank").toString(), target);
                String rankMsg = Main.plugin.getConfig().getString("Groups." + args[1] + ".message");
                String appendedRankMSG = rankMsg.replace("&", "§");
                target.sendMessage(ChatColor.GREEN + "You are now" + " " + appendedRankMSG + ChatColor.GREEN + "!");
                sender.sendMessage(ChatColor.GREEN + target.getName() + "'s rank is now:" + " " + args[1] + "!");
            } catch (Error e) {
                System.out.println(ChatColor.RED + " " + "An error occured");
                return true;
            }
            return true;
        }
        sender.sendMessage(ChatColor.RED + "You cannot do that.");
        return true;
    }
}