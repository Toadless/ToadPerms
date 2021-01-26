package uk.toadl3ss.Toadperms.Blocks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class breakBlock implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("tp.blockBreak")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + " " + "You do not have permission to do that.");
            return;
        }
        return;
    }
}
