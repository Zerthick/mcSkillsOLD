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
