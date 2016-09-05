package io.github.zerthick.mcskills.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

public class McSkillsExperienceGainEvent extends McSkillsExperienceEvent {

    public McSkillsExperienceGainEvent(Player player, String skillID, long experience, Cause cause) {
        super(player, skillID, experience, cause);
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

    public long getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
