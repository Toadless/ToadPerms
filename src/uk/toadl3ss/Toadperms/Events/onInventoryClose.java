package uk.toadl3ss.Toadperms.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import uk.toadl3ss.Toadperms.Commands.Grant;
import uk.toadl3ss.Toadperms.Commands.Grants;
import uk.toadl3ss.Toadperms.GUIS.ConfirmGUI;
import uk.toadl3ss.Toadperms.GUIS.GrantsGUI;
import uk.toadl3ss.Toadperms.GUIS.RankGUI;

public class onInventoryClose implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        if (event.getInventory() == null) {
            return;
        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // Clear the inventory hashmap if the gui gets closed.
                        if (event.getInventory().getHolder() instanceof RankGUI) {
                            Player target = Grant.grantHashMap.get(player.getUniqueId());
                            if (target == null) {
                                return;
                            }
                            try {
                                Grant.grantHashMap.remove(player.getUniqueId(), target);
                            } catch (Error e) {
                                return;
                            }
                        }
                        if (event.getInventory().getHolder() instanceof ConfirmGUI) {
                            Player target = RankGUISelect.rankHashMap.get(player.getUniqueId());
                            String rank = RankGUISelect.rankNameHashMap.get(player.getUniqueId());
                            if (target == null || rank == null) {
                                return;
                            }
                            try {
                                RankGUISelect.rankHashMap.remove(player.getUniqueId(), target);
                                RankGUISelect.rankNameHashMap.remove(player.getUniqueId(), rank);
                            } catch (Error e) {
                                return;
                            }
                        }
                        if (event.getInventory().getHolder() instanceof ConfirmGUISelect) {
                            Player target = GrantsGUISelect.Grants.get(player.getUniqueId());
                            if (target == null) {
                                return;
                            }
                            try {
                                GrantsGUISelect.Grants.remove(player.getUniqueId(), target);
                            } catch (Error e) {
                                return;
                            }
                        }
                        if (event.getInventory().getHolder() instanceof GrantsGUI) {
                            Player target = GrantsGUISelect.Grants.get(player.getUniqueId());
                            if (target == null) {
                                return;
                            }
                            try {
                                GrantsGUISelect.Grants.remove(player.getUniqueId(), target);
                            } catch (Error e) {
                                return;
                            }
                        }
                    }
                },
                5000
        );
    }
}
