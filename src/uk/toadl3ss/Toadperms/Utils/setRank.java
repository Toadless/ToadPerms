package uk.toadl3ss.Toadperms.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Main;
import uk.toadl3ss.Toadperms.Permissions.Permissions;

import java.util.UUID;

public class setRank {
    public static void setRank(Player target, Player executor, String rank) {
     if (!executor.hasPermission("tp.anyRank")) {
         executor.sendMessage(ChatColor.RED + " " + "You need to have the permission:" + ChatColor.GREEN + " " + "tp.anyRank" + ChatColor.RESET + " " + "to perform this action.");
         return;
     }
     DBObject query = new BasicDBObject("uuid", target.getUniqueId());
     DBCursor cursor = database.user.find(query);

     if (cursor.one() == null) {
         return;
     }
     DBObject update = new BasicDBObject("rank", rank);
     update.put("uuid", target.getUniqueId());
     database.user.update(query, update);
     setupPermissions(target, cursor.one().get("rank").toString());
     setChatPrefix.setChatPrefix(cursor.one().get("rank").toString(), target);
     String rankMsg = Main.plugin.getConfig().getString("Groups." + rank + ".message");
     String appendedRankMSG = rankMsg.replace("&", "§");
     openScoreboard.openScoreboard(target);
     target.sendMessage(ChatColor.GREEN + "You are now" + " " + appendedRankMSG + ChatColor.GREEN + "!");
     executor.sendMessage(ChatColor.GREEN + target.getName() + "'s rank is now:" + " " + appendedRankMSG + ChatColor.GREEN + "!");
    }

    public static void setupPermissions(Player player, String rank) {
        permissionsSetter(player.getUniqueId(), rank);
    }
    public static void permissionsSetter(UUID uuid, String rank) {
        PermissionAttachment attachment = Permissions.playerPermissions.get(uuid);
        //Removing all perms
        for (String groups : Main.plugin.getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : Main.plugin.getConfig().getStringList("Groups." + groups + ".permissions")) {
                attachment.unsetPermission(permissions);
                if (permissions.endsWith("*")) {
                    if (permissions.equals("*")) {
                        for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                            attachment.unsetPermission(perm);
                        }
                    } else {
                        String search = permissions.substring(0, permissions.length() - 1);
                        for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                            if (perm.getName().startsWith(search)) {
                                attachment.unsetPermission(perm);
                            }
                        }
                    }
                }
            }
        }
        //Adding groups perms
        for (String permissions : Main.plugin.getConfig().getStringList("Groups." + rank + ".permissions")) {
            attachment.setPermission(permissions, true);
            if (permissions.endsWith("*")) {
                if (permissions.equals("*")) {
                    for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                        attachment.setPermission(perm, true);
                    }
                } else {
                    String search = permissions.substring(0, permissions.length() - 1);
                    for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                        if (perm.getName().startsWith(search)) {
                            attachment.setPermission(perm, true);
                        }
                    }
                }
            }
        }
    }
}