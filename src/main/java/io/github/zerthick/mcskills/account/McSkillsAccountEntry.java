package io.github.zerthick.mcskills.account;

public class McSkillsAccountEntry {

    int level;
    long experience;

    public McSkillsAccountEntry(int level, long experience) {
        this.level = level;
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }
}
