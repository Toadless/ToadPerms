package uk.toadl3ss.Toadperms.GUIS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrantConfirmGUI implements InventoryHolder {
    private Inventory inv;
    public GrantConfirmGUI(Player target) {
        inv = Bukkit.createInventory(this, 45, ChatColor.DARK_GRAY + "Confirm for" + " " + ChatColor.DARK_AQUA.toString() + ChatColor.ITALIC.toString() + target.getName());
        init(target);
    }

    private void init(Player player) {
        ItemStack item;

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(0, item);
        inv.setItem(1, item);
        inv.setItem(2, item);
        inv.setItem(3, item);
        inv.setItem(4, item);
        inv.setItem(5, item);
        inv.setItem(6, item);
        inv.setItem(7, item);
        inv.setItem(8, item);
        inv.setItem(9, item);

        List<String> aLore = new ArrayList<>();
        aLore.add(ChatColor.GRAY + "Remove" + " " + ChatColor.DARK_AQUA + player.getName() + "'s " + ChatColor.GRAY + "rank.");
        String aName = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Confirm Action";

        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(10, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(11, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(12, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(13, item);

        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(14, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(15, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(16, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(17, item);
        inv.setItem(18, item);

        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(19, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(20, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(21, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(22, item);

        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(23, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(24, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(25, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(26, item);
        inv.setItem(27, item);

        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(28, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(29, item);
        item = createItem(aName, Material.STAINED_CLAY, (byte)13, aLore);
        inv.setItem(30, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(31, item);

        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(32, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(33, item);
        item = createItem("Deny", Material.STAINED_CLAY, (byte)14, Collections.singletonList("Cancel this request."));
        inv.setItem(34, item);

        item = createItem("", Material.STAINED_GLASS_PANE, (byte)15, Collections.singletonList(""));
        inv.setItem(35, item);
        inv.setItem(36, item);
        inv.setItem(37, item);
        inv.setItem(38, item);
        inv.setItem(39, item);
        inv.setItem(40, item);
        inv.setItem(41, item);
        inv.setItem(42, item);
        inv.setItem(43, item);
        inv.setItem(44, item);

    }

    private ItemStack createItem(String name, Material material, byte matByte, List<String> lore) {
        ItemStack item = new ItemStack(material, 1, matByte);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
