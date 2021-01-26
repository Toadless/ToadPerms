package uk.toadl3ss.Toadperms.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import uk.toadl3ss.Toadperms.Commands.Grant;
import uk.toadl3ss.Toadperms.GUIS.ConfirmGUI;
import uk.toadl3ss.Toadperms.GUIS.RankGUI;
import uk.toadl3ss.Toadperms.Main;

import java.util.HashMap;
import java.util.UUID;

public class RankGUISelect implements Listener {
    public static HashMap<UUID, Player> rankHashMap = new HashMap<>();
    public static HashMap<UUID, String> rankNameHashMap = new HashMap<>();
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof RankGUI) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getData().getItemType() == Material.AIR) {
                return;
            }
            final String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
            Player player = (Player) e.getWhoClicked();
            final var configItemName = Main.plugin.getConfig().getString("Groups." + itemName);
            if (configItemName == null) {
                player.closeInventory();
                return;
            }
            Player target = Grant.grantHashMap.get(player.getUniqueId());
            Grant.grantHashMap.remove(player.getUniqueId(), target);
            player.closeInventory();
            ConfirmGUI gui = new ConfirmGUI(target);
            rankHashMap.put(player.getUniqueId(), target);
            rankNameHashMap.put(player.getUniqueId(), itemName);
            player.openInventory(gui.getInventory());
            return;
        }
        return;
    }
}