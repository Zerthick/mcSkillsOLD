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
