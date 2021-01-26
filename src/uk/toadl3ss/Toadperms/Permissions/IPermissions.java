package uk.toadl3ss.Toadperms.Permissions;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public interface IPermissions {
    // The method of setting the players permissions
    void permissionsSetter(UUID uuid, String rank);

    // The method of setting the players permissions
    void setupPermissions(Player player, String rank);

    // This gets called when a player leaves the server
    void leave(PlayerQuitEvent event);

    // This gets called when a player joins the server
    void join(PlayerJoinEvent event);
}