package io.github.zerthick.mcskills.experience;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.account.McSkillsAccount;
import io.github.zerthick.mcskills.account.McSkillsAccountManager;
import io.github.zerthick.mcskills.event.McSkillsExperienceGainEvent;
import io.github.zerthick.mcskills.event.McSkillsLevelUpEvent;
import io.github.zerthick.mcskills.skill.ISkill;
import io.github.zerthick.mcskills.skill.SkillManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

import java.util.Optional;
import java.util.UUID;

public class ExperienceManager {

    private McSkills plugin;
    private McSkillsAccountManager mcSkillsAccountManager;
    private SkillManager skillManager;

    private IExperienceFormula experienceFormula;

    public ExperienceManager(McSkills plugin) {
        this.plugin = plugin;
        mcSkillsAccountManager = plugin.getMcSkillsAccountManager();
        skillManager = plugin.getSkillManager();
        experienceFormula = new LinearFormula(100, 10);
    }

    public void addSkillExperience(Player player, ISkill skill, long experience, Cause cause) {

        Optional<McSkillsAccount> accountOptional = mcSkillsAccountManager.getAccount(player.getUniqueId());

        if (accountOptional.isPresent()) {

            McSkillsAccount mcSkillsAccount = accountOptional.get();
            String skillID = skill.getSkillID();

            long currentExperience = mcSkillsAccount.getSkillExperience(skillID);
            int currentLevel = getSkillLevel(player.getUniqueId(), skillID);
            long currentLevelExperience = experienceFormula.getLevelExperience(currentLevel);

            McSkillsExperienceGainEvent mcSkillExperienceGainEvent =
                    new McSkillsExperienceGainEvent(player, skill.getSkillID(), experience,
                            cause.merge(Cause.builder().named("skill", skill).build()));
            Sponge.getEventManager().post(mcSkillExperienceGainEvent);

            if (!mcSkillExperienceGainEvent.isCancelled()) {

                long deltaExperience = currentExperience + mcSkillExperienceGainEvent.getExperience();
                if (deltaExperience < currentLevelExperience) {
                    mcSkillsAccount.setSkillExperience(skillID, currentExperience + mcSkillExperienceGainEvent.getExperience());
                } else {

                    int targetLevel = currentLevel;
                    long targetExperience = deltaExperience;

                    while (targetExperience >= experienceFormula.getLevelExperience(targetLevel)) {
                        targetExperience -= experienceFormula.getLevelExperience(targetLevel);
                        targetLevel++;
                    }

                    McSkillsLevelUpEvent mcSkillsLevelUpEvent = new McSkillsLevelUpEvent(player, skillID, targetLevel, targetExperience,
                            cause.merge(Cause.builder().named("skill", skill).build()));

                    Sponge.getEventManager().post(mcSkillsLevelUpEvent);
                    if (!mcSkillsLevelUpEvent.isCancelled()) {
                        mcSkillsAccount.setSkillLevel(skillID, mcSkillsLevelUpEvent.getLevel());
                        mcSkillsAccount.setSkillExperience(skillID, mcSkillsLevelUpEvent.getRemainingExperience());
                    }
                }
            }
        }
    }

    public long getSkillExperience(UUID playerUniqueIdentifier, String skillID) {

        Optional<McSkillsAccount> accountOptional = mcSkillsAccountManager.getAccount(playerUniqueIdentifier);

        if (accountOptional.isPresent()) {
            return accountOptional.get().getSkillExperience(skillID);
        }

        return 0;
    }

    public int getSkillLevel(UUID playerUniqueIdentifier, String skillID) {

        Optional<McSkillsAccount> accountOptional = mcSkillsAccountManager.getAccount(playerUniqueIdentifier);

        if (accountOptional.isPresent()) {
            return accountOptional.get().getSkillLevel(skillID);
        }

        return 0;
    }

}
