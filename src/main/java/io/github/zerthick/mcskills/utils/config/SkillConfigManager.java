package io.github.zerthick.mcskills.utils.config;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.skill.ISkill;
import ninja.leaping.configurate.ConfigurationNode;
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
    private File skillConfigFile;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private ConfigurationNode skillConfig;

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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ObjectMappingException e) {
                e.printStackTrace();
            }
        }

        try {
            skill.fromConfig(loader.load());
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
