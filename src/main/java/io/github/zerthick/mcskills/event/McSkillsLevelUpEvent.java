/*
 * Copyright (C) 2016  Zerthick
 *
 * This file is part of mcSkills.
 *
 * mcSkills is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * mcSkills is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mcSkills.  If not, see <http://www.gnu.org/licenses/>.
 */

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
