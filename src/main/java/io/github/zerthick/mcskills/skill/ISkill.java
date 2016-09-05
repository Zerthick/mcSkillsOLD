package io.github.zerthick.mcskills.skill;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

public interface ISkill {

    String getSkillID();

    String getSkillName();

    String getSkillDescription();

    void registerListeners();

    CommentedConfigurationNode setUpDefaultConfig() throws ObjectMappingException, IOException;

    void fromConfig(ConfigurationNode skillConfig) throws ObjectMappingException;
}
