package maxdevos.maxcraft.raidSystem;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

class RaidScoreboard {

    private Scoreboard board;
    private Objective objective;

    RaidScoreboard(){

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        assert sm != null;
        board = sm.getNewScoreboard();

        objective = board.registerNewObjective("kills", "dummy");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cRaid Kills");

    }

    void updateScoreboard(ArrayList<RaidPlayer> players){
        for(RaidPlayer p:players){
            Score kills = objective.getScore(p.getPlayer());
            kills.setScore(p.getKills());
            p.processScore(board);
        }
    }

    void restoreScoreboard(){
        board.resetScores("kills");
        board.clearSlot(DisplaySlot.SIDEBAR);
        board.clearSlot(DisplaySlot.BELOW_NAME);
    }


}
