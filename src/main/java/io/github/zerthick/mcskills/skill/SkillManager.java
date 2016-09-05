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

package io.github.zerthick.mcskills.skill;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.utils.config.SkillConfigManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SkillManager {

    private McSkills plugin;
    private SkillConfigManager skillConfigManager;

    private Map<String, ISkill> skillMap;

    public SkillManager(McSkills plugin) {
        this.plugin = plugin;
        skillConfigManager = plugin.getSkillConfigManager();
        skillMap = new HashMap<>();
    }

    public void registerSkill(ISkill skill) {
        skillConfigManager.loadSkillConfig(skill);
        skill.registerListeners();
        skillMap.put(skill.getSkillID(), skill);
    }

    public Optional<ISkill> getSkill(String skillID) {
        return Optional.ofNullable(skillMap.get(skillID));
    }

    public boolean isSkill(String skillID) {
        return getSkill(skillID).isPresent();
    }

    public Collection<ISkill> getSkills() {
        return skillMap.values();
    }
}
