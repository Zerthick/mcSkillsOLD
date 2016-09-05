package io.github.zerthick.mcskills.account;

import java.util.Map;
import java.util.UUID;

public class McSkillsAccount {

    UUID playerUniqueIdentifier;
    Map<String, McSkillsAccountEntry> skillMap;

    public McSkillsAccount(UUID playerUniqueIdentifier, Map<String, McSkillsAccountEntry> skillMap) {
        this.playerUniqueIdentifier = playerUniqueIdentifier;
        this.skillMap = skillMap;
    }

    public UUID getPlayerUniqueIdentifier() {
        return playerUniqueIdentifier;
    }

    public Map<String, McSkillsAccountEntry> getSkillMap() {
        return skillMap;
    }

    public int getSkillLevel(String skillID) {
        McSkillsAccountEntry entry = skillMap.get(skillID);
        return entry != null ? entry.getLevel() : 0;
    }

    public long getSkillExperience(String skillID) {
        McSkillsAccountEntry entry = skillMap.get(skillID);
        return entry != null ? entry.getExperience() : 0;
    }

    public void setSkillLevel(String skillID, int level) {
        McSkillsAccountEntry entry = skillMap.getOrDefault(skillID, new McSkillsAccountEntry(0, 0));
        entry.setLevel(level);
        skillMap.put(skillID, entry);
    }

    public void setSkillExperience(String skillID, long experience) {
        McSkillsAccountEntry entry = skillMap.getOrDefault(skillID, new McSkillsAccountEntry(0, 0));
        entry.setExperience(experience);
        skillMap.put(skillID, entry);
    }
}
