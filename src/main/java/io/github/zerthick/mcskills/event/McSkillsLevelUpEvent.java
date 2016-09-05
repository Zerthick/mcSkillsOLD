package io.github.zerthick.mcskills.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

public class McSkillsLevelUpEvent extends McSkillsLevelEvent {

    private long remainingExperience;

    public McSkillsLevelUpEvent(Player player, String skillID, int level, long remainingExperience, Cause cause) {
        super(player, skillID, level, cause);
        this.remainingExperience = remainingExperience;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public Player getTargetEntity() {
        return player;
    }

    @Override
    public Cause getCause() {
        return cause;
    }

    public String getSkillID() {
        return skillID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getRemainingExperience() {
        return remainingExperience;
    }

    public void setRemainingExperience(long remainingExperience) {
        this.remainingExperience = remainingExperience;
    }
}
