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

package io.github.zerthick.mcskills.account;

import java.util.Map;
import java.util.UUID;

public class McSkillsAccount {

    UUID playerUniqueIdentifier;
    Map<String, McSkillsAccountEntry> skillMap;

    public McSkillsAccount(UUID playerUniqueIdentifier, Map<String, McSkillsAccountEntry> skillMap) {
        this.playerUniqueIdentifier = playerUniqueIdentifier;
        this.skillMap = skillMap;
    }

    public UUID getPlayerUniqueIdentifier() {
        return playerUniqueIdentifier;
    }

    public Map<String, McSkillsAccountEntry> getSkillMap() {
        return skillMap;
    }

    public int getSkillLevel(String skillID) {
        McSkillsAccountEntry entry = skillMap.get(skillID);
        return entry != null ? entry.getLevel() : 0;
    }

    public long getSkillExperience(String skillID) {
        McSkillsAccountEntry entry = skillMap.get(skillID);
        return entry != null ? entry.getExperience() : 0;
    }

    public void setSkillLevel(String skillID, int level) {
        McSkillsAccountEntry entry = skillMap.getOrDefault(skillID, new McSkillsAccountEntry(0, 0));
        entry.setLevel(level);
        skillMap.put(skillID, entry);
    }

    public void setSkillExperience(String skillID, long experience) {
        McSkillsAccountEntry entry = skillMap.getOrDefault(skillID, new McSkillsAccountEntry(0, 0));
        entry.setExperience(experience);
        skillMap.put(skillID, entry);
    }
}
