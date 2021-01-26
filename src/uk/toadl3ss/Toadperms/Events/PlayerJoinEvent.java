package uk.toadl3ss.Toadperms.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.toadl3ss.Toadperms.Utils.openScoreboard;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        openScoreboard.openScoreboard(event.getPlayer());
    }
}