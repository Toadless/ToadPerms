package uk.toadl3ss.Toadperms.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import uk.toadl3ss.Toadperms.GUIS.ConfirmGUI;
import uk.toadl3ss.Toadperms.Utils.setRank;

public class ConfirmGUISelect implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof ConfirmGUI) {
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
            Player target = RankGUISelect.rankHashMap.get(player.getUniqueId());
            String rank = RankGUISelect.rankNameHashMap.get(player.getUniqueId());
            setRank.setRank(target, player, rank);
            RankGUISelect.rankNameHashMap.remove(player.getUniqueId(), rank);
            RankGUISelect.rankHashMap.remove(player.getUniqueId(), target);
            return;
        }
        return;
    }
}
