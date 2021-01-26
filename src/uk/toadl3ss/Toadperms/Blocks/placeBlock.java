package uk.toadl3ss.Toadperms.Blocks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class placeBlock implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("tp.blockPlace")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + " " + "You do not have permission to do that.");
            return;
        }
        return;
    }
}
