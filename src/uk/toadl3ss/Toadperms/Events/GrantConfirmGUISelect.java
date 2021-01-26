package uk.toadl3ss.Toadperms.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import uk.toadl3ss.Toadperms.GUIS.ConfirmGUI;
import uk.toadl3ss.Toadperms.GUIS.GrantConfirmGUI;
import uk.toadl3ss.Toadperms.Main;
import uk.toadl3ss.Toadperms.Utils.setRank;

public class GrantConfirmGUISelect implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof GrantConfirmGUI) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getData().getItemType() == Material.AIR) {
                return;
            }
            final String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
            Player player = (Player) e.getWhoClicked();
            player.closeInventory();
            if (itemName == "Deny") {
                return;
            }
            if (itemName == null) {
                return;
            }
            Player target = GrantsGUISelect.Grants.get(player.getUniqueId());
            if (target == null) {
                return;
            }
            String targetName = target.getName();
            String defaultRank = Main.plugin.getConfig().getString("default");
            setRank.setRank(target, player, defaultRank);
            GrantsGUISelect.Grants.remove(player.getUniqueId(), target);
            return;
        }
        return;
    }
}
