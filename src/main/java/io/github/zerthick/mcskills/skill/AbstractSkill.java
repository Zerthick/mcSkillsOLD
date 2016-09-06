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

import com.google.common.reflect.TypeToken;
import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.experience.ExperienceManager;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSkill implements ISkill {

    protected McSkills plugin;
    protected ExperienceManager experienceManager;

    protected String skillName;
    protected String skillDescription;

    protected Map<String, Integer> experienceMap;

    protected AbstractSkill(McSkills plugin) {
        this.plugin = plugin;
        experienceManager = plugin.getExperienceManager();
        experienceMap = new HashMap<>();
    }

    public void registerListeners() {
        Sponge.getEventManager().registerListeners(plugin, this);
    }

    public CommentedConfigurationNode setUpDefaultConfig() throws ObjectMappingException, IOException {
        Asset defaultConfig = plugin.getInstance().getAsset(getSkillID() + ".config").get();
        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setURL(defaultConfig.getUrl()).build();
        return loader.load();
    }

    public void fromConfig(ConfigurationNode skillConfig) throws ObjectMappingException {

        skillName = skillConfig.getNode("Name").getString("Unknown");
        skillDescription = skillConfig.getNode("Description").getString("Unknown");

        experienceMap = skillConfig.getNode("ExpMap").getValue(new TypeToken<Map<String, Integer>>() {
        }, new HashMap<>());
    }
}
