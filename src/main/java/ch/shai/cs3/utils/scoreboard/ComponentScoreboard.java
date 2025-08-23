package ch.shai.cs3.utils.scoreboard;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class ComponentScoreboard {

    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Map<Integer, String> lines = new HashMap<>();

    public ComponentScoreboard(Player player, Component title) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    public void setLine(int score, String text) {
        // Supprimer l'ancienne ligne si elle existe
        String oldLine = lines.get(score);
        if (oldLine != null) {
            scoreboard.resetScores(oldLine);
        }

        // Ajouter la nouvelle ligne
        objective.getScore(text).setScore(score);
        lines.put(score, text);
    }

    public void removeLine(int score) {
        String oldLine = lines.remove(score);
        if (oldLine != null) {
            scoreboard.resetScores(oldLine);
        }
    }
}
