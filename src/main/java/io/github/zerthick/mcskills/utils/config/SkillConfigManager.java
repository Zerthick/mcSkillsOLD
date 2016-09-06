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

package io.github.zerthick.mcskills.utils.config;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.skill.ISkill;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public class SkillConfigManager {

    private McSkills plugin;
    private Logger logger;

    public SkillConfigManager(McSkills plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public void loadSkillConfig(ISkill skill) {
        File skillConfigFile = new File(plugin.getDefaultConfigDir().toFile(), skill.getSkillID() + ".config");
        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(skillConfigFile).build();

        if (!skillConfigFile.exists()) {
            try {
                loader.save(skill.setUpDefaultConfig());
            } catch (IOException | ObjectMappingException e) {
                logger.warn("Error generating " + skill.getSkillID() + " default config! Error:" + e.getMessage());
            }
        }

        try {
            skill.fromConfig(loader.load());
        } catch (ObjectMappingException | IOException e) {
            logger.warn("Error loading " + skill.getSkillID() + " config! Error:" + e.getMessage());
        }
    }
}
