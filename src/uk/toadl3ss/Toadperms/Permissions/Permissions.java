package uk.toadl3ss.Toadperms.Permissions;

import java.util.HashMap;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import uk.toadl3ss.Toadperms.Main;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Utils.setChatPrefix;

public class Permissions implements Listener, IPermissions {
    public static HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
    @EventHandler
    public void join(PlayerJoinEvent event) {
        final String defaultRank = Main.plugin.getConfig().getString("default");
        final Player player = event.getPlayer();
        DBObject query = new BasicDBObject("uuid", player.getUniqueId());
        DBCursor cursor = database.user.find(query);
        if (cursor.one() == null) {
            System.out.println(player.getUniqueId());
            DBObject user = new BasicDBObject("uuid", player.getUniqueId())
                    .append("rank", defaultRank);
            database.user.insert(user);
            setupPermissions(player, defaultRank);
            setChatPrefix.setChatPrefix(defaultRank, player);
            return;
        }
        setupPermissions(player, cursor.one().get("rank").toString());
        setChatPrefix.setChatPrefix(cursor.one().get("rank").toString(), player);
    }


    @EventHandler
    public void leave(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        playerPermissions.remove(player.getUniqueId());
    }


    public void setupPermissions(Player player, String rank) {
        PermissionAttachment attachment = player.addAttachment(Main.plugin);
        this.playerPermissions.put(player.getUniqueId(), attachment);
        permissionsSetter(player.getUniqueId(), rank);
    }

    public void permissionsSetter(UUID uuid, String rank) {
        PermissionAttachment attachment = this.playerPermissions.get(uuid);
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