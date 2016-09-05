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
