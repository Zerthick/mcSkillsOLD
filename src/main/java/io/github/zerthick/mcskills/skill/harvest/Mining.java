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

package io.github.zerthick.mcskills.skill.harvest;

import io.github.zerthick.mcskills.McSkills;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

public class Mining extends HarvestSkill {

    private final String skillID = "Mining";

    public Mining(McSkills plugin) {
        super(plugin);
        skillName = "Mining";
    }

    @Override
    public String getSkillID() {
        return skillID;
    }

    @Override
    public String getSkillName() {
        return skillName;
    }

    @Override
    public String getSkillDescription() {
        return skillDescription;
    }

    @Override
    public CommentedConfigurationNode setUpDefaultConfig() throws ObjectMappingException, IOException {
        return super.setUpDefaultConfig();
    }

    @Override
    public void fromConfig(ConfigurationNode skillConfig) throws ObjectMappingException {
        super.fromConfig(skillConfig);
    }
}
