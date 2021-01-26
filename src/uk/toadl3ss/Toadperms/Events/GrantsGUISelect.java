package uk.toadl3ss.Toadperms.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import uk.toadl3ss.Toadperms.GUIS.ConfirmGUI;
import uk.toadl3ss.Toadperms.GUIS.GrantConfirmGUI;
import uk.toadl3ss.Toadperms.GUIS.GrantsGUI;
import uk.toadl3ss.Toadperms.Utils.setRank;

import java.util.HashMap;
import java.util.UUID;

public class GrantsGUISelect implements Listener {
    public static HashMap<UUID, Player> Grants = new HashMap<>();
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof GrantsGUI) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName() == "Close") {
                e.getWhoClicked().closeInventory();
                return;
            }
            if (e.getCurrentItem().getData().getItemType() == Material.STAINED_GLASS_PANE) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("")) {
                return;
            }
            Player target = Grants.get(e.getWhoClicked().getUniqueId());
            Player player = (Player) e.getWhoClicked();
            if (target == null) {
                return;
            }
            GrantConfirmGUI gui = new GrantConfirmGUI(target);
            player.openInventory(gui.getInventory());
        }
        return;
    }
}