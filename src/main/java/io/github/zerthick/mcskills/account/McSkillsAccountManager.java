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

package io.github.zerthick.mcskills.account;

import io.github.zerthick.mcskills.McSkills;

import java.util.*;

public class McSkillsAccountManager {

    private McSkills plugin;

    private Map<UUID, McSkillsAccount> accountMap;

    public McSkillsAccountManager(McSkills plugin) {
        this.plugin = plugin;
        accountMap = new HashMap<>();
    }

    public void addAccount(McSkillsAccount mcSkillsAccount) {
        accountMap.put(mcSkillsAccount.getPlayerUniqueIdentifier(), mcSkillsAccount);
    }

    public Optional<McSkillsAccount> removeAccount(UUID playerUniqueIdentifier) {
        return Optional.ofNullable(accountMap.remove(playerUniqueIdentifier));
    }

    public Optional<McSkillsAccount> getAccount(UUID playerUniqueIdentifier) {
        return Optional.ofNullable(accountMap.get(playerUniqueIdentifier));
    }

    public boolean hasAccount(UUID playerUniqueIndentifier) {
        return getAccount(playerUniqueIndentifier).isPresent();
    }

    public Collection<McSkillsAccount> getAccounts() {
        return accountMap.values();
    }
}
