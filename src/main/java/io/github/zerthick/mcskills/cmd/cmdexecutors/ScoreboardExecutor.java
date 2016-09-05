package io.github.zerthick.mcskills.cmd.cmdexecutors;

import io.github.zerthick.mcskills.McSkills;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class ScoreboardExecutor extends AbstractCmdExecutor {

    public ScoreboardExecutor(McSkills plugin) {
        super(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if (src instanceof Player) {
            Player player = (Player) src;
            skillManager.getSkills().forEach(s -> player.sendMessage(Text.of(s.getSkillName(), " Exp: ", experienceManager.getSkillExperience(player.getUniqueId(), s.getSkillID())
                    , " Level: ", experienceManager.getSkillLevel(player.getUniqueId(), s.getSkillID()))));
        }

        return CommandResult.success();
    }
}
