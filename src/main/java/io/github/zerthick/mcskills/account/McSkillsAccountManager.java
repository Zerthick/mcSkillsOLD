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
