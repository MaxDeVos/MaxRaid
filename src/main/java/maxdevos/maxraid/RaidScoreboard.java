package maxdevos.maxraid;

import maxdevos.maxraid.player.RaidPlayer;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class RaidScoreboard {

    private final Scoreboard board;
    private final Objective objective;

    public RaidScoreboard(){

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        assert sm != null;
        board = sm.getNewScoreboard();

        objective = board.registerNewObjective("kills", "dummy", "dummy2");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cRaid Kills");

    }

    public void updateScoreboard(ArrayList<RaidPlayer> players){
        for(RaidPlayer p:players){
            Score kills = objective.getScore(p.getPlayer());
            kills.setScore(p.getKills());
            p.processScore(board);
        }
    }

    public void restoreScoreboard(){
        board.resetScores("kills");
        board.clearSlot(DisplaySlot.SIDEBAR);
        board.clearSlot(DisplaySlot.BELOW_NAME);
    }


}
