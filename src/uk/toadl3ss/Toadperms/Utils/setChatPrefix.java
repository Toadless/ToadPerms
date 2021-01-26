package uk.toadl3ss.Toadperms.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.Main;

public class setChatPrefix {
    public static void setChatPrefix(String rank, Player target) {
        final String defaultRank = Main.plugin.getConfig().getString("Groups." + "default");
        if (rank == "default") {
            final String prefix = Main.plugin.getConfig().getString("Groups." + defaultRank + ".prefix");
            target.setDisplayName(prefix + target.getName() + ChatColor.RESET);
        } else {
            final String prefix = Main.plugin.getConfig().getString("Groups." + rank + ".prefix");
            String ammendedPrefix = prefix.replace("&", "§");
            target.setDisplayName(ammendedPrefix + " " + target.getName() + ChatColor.RESET); //For head target.setCustomName
        }
    }
}