package io.github.zerthick.mcskills.cmd;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.cmd.cmdexecutors.ScoreboardExecutor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;

public class McSkillsCmdRegister {

    private McSkills plugin;

    public McSkillsCmdRegister(McSkills plugin) {
        this.plugin = plugin;
    }

    public void registerCmds() {

        CommandSpec mcsScorboardCommand = CommandSpec.builder()
                .executor(new ScoreboardExecutor(plugin))
                .build();

        CommandSpec mcsCommand = CommandSpec.builder()
                .child(mcsScorboardCommand, "scoreboard", "sb")
                .build();

        Sponge.getCommandManager().register(plugin, mcsCommand, "mcSkills", "mcs");
    }
}
