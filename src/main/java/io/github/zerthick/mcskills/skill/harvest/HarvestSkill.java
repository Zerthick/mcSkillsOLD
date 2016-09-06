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

package io.github.zerthick.mcskills.skill.harvest;

import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.skill.AbstractSkill;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class HarvestSkill extends AbstractSkill {

    protected Pattern variantRegex = Pattern.compile("(variant=.*)[,\\]]");

    protected HarvestSkill(McSkills plugin) {
        super(plugin);
    }

    @Listener
    public void onBlockBreak(ChangeBlockEvent.Break event, @Root Player player) {
        List<BlockSnapshot> blocks = event.getTransactions().stream()
                .map(Transaction::getOriginal).filter(s -> experienceMap.containsKey(processBlockStateID(s.getState())) && !s.getCreator().isPresent())
                .collect(Collectors.toList());
        blocks.forEach(s -> experienceManager.addSkillExperience(player, this, experienceMap.get(processBlockStateID(s.getState())), event.getCause()));
    }

    protected String processBlockStateID(BlockState blockState) {
        String id = blockState.getType().getId();
        Matcher variantMatcher = variantRegex.matcher(blockState.getId());
        if (variantMatcher.find()) {
            id += '[' + variantMatcher.group(1) + ']';
        }
        return id;
    }
}
