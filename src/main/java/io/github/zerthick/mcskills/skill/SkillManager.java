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
