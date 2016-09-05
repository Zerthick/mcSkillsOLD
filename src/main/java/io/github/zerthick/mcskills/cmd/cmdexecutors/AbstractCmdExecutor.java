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

package io.github.zerthick.mcskills.cmd.cmdexecutors;


import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.account.McSkillsAccountManager;
import io.github.zerthick.mcskills.experience.ExperienceManager;
import io.github.zerthick.mcskills.skill.SkillManager;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.plugin.PluginContainer;

public abstract class AbstractCmdExecutor implements CommandExecutor {

    protected McSkills plugin;
    protected PluginContainer container;

    protected McSkillsAccountManager mcSkillsAccountManager;
    protected SkillManager skillManager;
    protected ExperienceManager experienceManager;

    public AbstractCmdExecutor(McSkills plugin) {

        this.plugin = plugin;
        this.container = plugin.getInstance();

        this.mcSkillsAccountManager = plugin.getMcSkillsAccountManager();
        this.skillManager = plugin.getSkillManager();
        this.experienceManager = plugin.getExperienceManager();
    }
}
