package uk.toadl3ss.Toadperms.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bukkit.entity.Player;
import uk.toadl3ss.Toadperms.Database.database;
import uk.toadl3ss.Toadperms.Main;
import uk.toadl3ss.Toadperms.Scoreboards.Scoreboard;

public class openScoreboard {
    public static void openScoreboard(Player target) {
        String scoreboardStatus = Main.plugin.getConfig().getString("Scoreboard.enabled");
        Boolean status = Boolean.parseBoolean(scoreboardStatus);
        if (!status) {
            return;
        }
        DBObject query = new BasicDBObject("uuid", target.getUniqueId());
        DBCursor cursor = database.user.find(query);
        if (cursor.one() == null) {
            return;
        }
        String rank = (String) cursor.one().get("rank");
        String playerRank = Main.plugin.getConfig().getString("Groups." + rank + ".message");
        Scoreboard.createBoard(target, playerRank);
    }
}