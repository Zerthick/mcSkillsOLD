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
