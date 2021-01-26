package uk.toadl3ss.Toadperms.Scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import uk.toadl3ss.Toadperms.Main;

public class Scoreboard {
    public static void createBoard(Player player, String playerRank) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("permboard", "");

        String title = (String) Main.plugin.getConfig().getString("Scoreboard.title");
        String appendedTitle = title.replace("&", "§");

        String websiteStr = (String) Main.plugin.getConfig().getString("Scoreboard.website");
        String appendedWebsite = websiteStr.replace("&", "§");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GREEN + appendedTitle);

        String appendedPlayerRank = playerRank.replace("&", "§");

        Score rankTitle = obj.getScore(ChatColor.GOLD + "Rank:");
        Score rank = obj.getScore(ChatColor.GRAY + appendedPlayerRank);
        Score websiteTitle = obj.getScore(ChatColor.GOLD + "Website:");
        Score website = obj.getScore(ChatColor.GRAY + appendedWebsite);;

        rankTitle.setScore(4);  //Rank:
        rank.setScore(3);       // {RANK}
        websiteTitle.setScore(2); // Website:
        website.setScore(1);    // {WEBSITE}

        player.setScoreboard(board);
    }
}