package io.github.zerthick.mcskills.event;


import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.entity.living.humanoid.player.TargetPlayerEvent;

public abstract class McSkillsLevelEvent implements TargetPlayerEvent, Cancellable {

    protected final Cause cause;
    protected final Player player;
    protected String skillID;
    protected int level;
    protected boolean cancelled = false;

    public McSkillsLevelEvent(Player player, String skillID, int level, Cause cause) {
        this.player = player;
        this.skillID = skillID;
        this.level = level;
        this.cause = cause;
    }
}
