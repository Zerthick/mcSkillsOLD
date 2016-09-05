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

package io.github.zerthick.mcskills;

import com.google.inject.Inject;
import io.github.zerthick.mcskills.account.McSkillsAccount;
import io.github.zerthick.mcskills.account.McSkillsAccountManager;
import io.github.zerthick.mcskills.cmd.McSkillsCmdRegister;
import io.github.zerthick.mcskills.experience.ExperienceManager;
import io.github.zerthick.mcskills.skill.SkillManager;
import io.github.zerthick.mcskills.skill.harvest.Mining;
import io.github.zerthick.mcskills.utils.config.AccountConfigManager;
import io.github.zerthick.mcskills.utils.config.SkillConfigManager;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;
import java.util.HashMap;

@Plugin(id = "mcskills",
        name = "McSkills",
        version = "0.0.1",
        description = "A Minecraft skills plugin")
public class McSkills {

    @Inject
    private Logger logger;
    @Inject
    private PluginContainer instance;

    //Config Stuff
    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConfig;
    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Inject
    @ConfigDir(sharedRoot = false)
    private Path defaultConfigDir;

    private AccountConfigManager accountConfigManager;
    private SkillConfigManager skillConfigManager;

    private McSkillsAccountManager mcSkillsAccountManager;
    private SkillManager skillManager;
    private ExperienceManager experienceManager;

    private McSkillsCmdRegister cmdRegister;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        accountConfigManager = new AccountConfigManager(this);
        skillConfigManager = new SkillConfigManager(this);

        mcSkillsAccountManager = new McSkillsAccountManager(this);
        skillManager = new SkillManager(this);
        experienceManager = new ExperienceManager(this);
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        skillManager.registerSkill(new Mining(this));

        //Register Commands
        cmdRegister = new McSkillsCmdRegister(this);
        cmdRegister.registerCmds();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        // Log Start Up to Console
        getLogger().info(
                instance.getName() + " version " + instance.getVersion().orElse("")
                        + " enabled!");
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        mcSkillsAccountManager.addAccount(new McSkillsAccount(event.getTargetEntity().getUniqueId(), new HashMap<>()));
    }

    @Listener
    public void onPlayerDisconnect(ClientConnectionEvent.Disconnect event) {
        mcSkillsAccountManager.removeAccount(event.getTargetEntity().getUniqueId());
    }

    public Logger getLogger() {
        return logger;
    }

    public PluginContainer getInstance() {
        return instance;
    }

    public Path getDefaultConfig() {
        return defaultConfig;
    }

    public ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
        return configLoader;
    }

    public Path getDefaultConfigDir() {
        return defaultConfigDir;
    }

    public McSkillsAccountManager getMcSkillsAccountManager() {
        return mcSkillsAccountManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public ExperienceManager getExperienceManager() {
        return experienceManager;
    }

    public AccountConfigManager getAccountConfigManager() {
        return accountConfigManager;
    }

    public SkillConfigManager getSkillConfigManager() {
        return skillConfigManager;
    }
}
