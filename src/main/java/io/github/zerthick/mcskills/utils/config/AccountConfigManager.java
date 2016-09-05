package io.github.zerthick.mcskills.utils.config;

import io.github.zerthick.mcskills.McSkills;
import org.slf4j.Logger;

public class AccountConfigManager {

    private McSkills plugin;
    private Logger logger;

    public AccountConfigManager(McSkills plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
}
