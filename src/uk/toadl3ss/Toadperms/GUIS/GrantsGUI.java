package uk.toadl3ss.Toadperms.GUIS;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrantsGUI implements InventoryHolder {
    private Inventory inv;
    public GrantsGUI(Player target) {
        String title = (ChatColor.DARK_GRAY + "Grants for" + " " + ChatColor.DARK_AQUA.toString() + ChatColor.ITALIC.toString() + target.getName());
        inv = Bukkit.createInventory(this, 9, title);
        init(target);
    }

    private void init(Player player) {
        ItemStack item;
        DBObject query = new BasicDBObject("uuid", player.getUniqueId());
        DBCursor cursor = database.user.find(query);
        String rank = (String) cursor.one().get("rank");
        String groupItem = Main.plugin.getConfig().getString("Groups." + rank + ".item");
        String groupByte = Main.plugin.getConfig().getString("Groups." + rank + ".byte");
        List<String> lore = new ArrayList<>();
        String rankPrefix = (String) Main.plugin.getConfig().getString("Groups." + rank + ".prefix");
        String appendedRankPrefix = rankPrefix.replace("&", "§");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() +  "---------------------");
        lore.add(ChatColor.YELLOW + "Prefix:" + " " + appendedRankPrefix);
        lore.add(ChatColor.YELLOW + "Grantable:" + " " + ChatColor.GOLD + "true");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "---------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Click to remove");
        item = createItem(rank, Material.getMaterial(groupItem), groupByte, lore);
        inv.setItem(inv.firstEmpty(), item);
        item = createItem("Close", Material.WOOL, "14", Collections.singletonList(ChatColor.RED + "Closes the GUI."));
        inv.setItem(8, item);
        item = createItem("", Material.STAINED_GLASS_PANE, "15", Collections.singletonList(""));
        inv.setItem(1, item);
        inv.setItem(2, item);
        inv.setItem(3, item);
        inv.setItem(4, item);
        inv.setItem(5, item);
        inv.setItem(6, item);
        inv.setItem(7, item);
    }

    private ItemStack createItem(String name, Material material, String objectByte, List<String> lore) {
        Byte itemByte = Byte.parseByte(objectByte);
        ItemStack item = new ItemStack(material, 1, (byte)itemByte);
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
