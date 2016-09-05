package io.github.zerthick.mcskills.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.entity.living.humanoid.player.TargetPlayerEvent;

public abstract class McSkillsExperienceEvent implements TargetPlayerEvent, Cancellable {

    protected final Cause cause;
    protected final Player player;
    protected String skillID;
    protected long experience;
    protected boolean cancelled = false;

    public McSkillsExperienceEvent(Player player, String skillID, long experience, Cause cause) {
        this.player = player;
        this.skillID = skillID;
        this.experience = experience;
        this.cause = cause;
    }
}
