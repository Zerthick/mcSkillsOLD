package io.github.zerthick.mcskills.skill.harvest;

import com.google.common.reflect.TypeToken;
import io.github.zerthick.mcskills.McSkills;
import io.github.zerthick.mcskills.experience.ExperienceManager;
import io.github.zerthick.mcskills.skill.ISkill;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.First;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class HarvestSkill implements ISkill {

    protected McSkills plugin;
    protected ExperienceManager experienceManager;

    protected String skillName;
    protected String skillDescription;

    protected Map<String, Integer> experienceMap;

    protected HarvestSkill(McSkills plugin) {
        this.plugin = plugin;
        experienceManager = plugin.getExperienceManager();
        experienceMap = new HashMap<>();
    }

    public void registerListeners() {
        Sponge.getEventManager().registerListeners(plugin, this);
    }

    @Listener
    public void onBlockBreak(ChangeBlockEvent.Break event, @First Player player) {
        event.getTransactions().stream().map(Transaction::getOriginal).forEach(s -> plugin.getLogger().info(s.getState().getType().getId()));

        List<BlockSnapshot> blocks = event.getTransactions().stream()
                .map(Transaction::getOriginal).filter(s -> experienceMap.containsKey(s.getState().getId()) && !s.getCreator().isPresent())
                .collect(Collectors.toList());
        blocks.forEach(s -> experienceManager.addSkillExperience(player, this, experienceMap.get(s.getState().getId()), event.getCause()));
    }

    public CommentedConfigurationNode setUpDefaultConfig() throws ObjectMappingException, IOException {
        Asset defaultConfig = plugin.getInstance().getAsset(getSkillID() + ".config").get();
        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setURL(defaultConfig.getUrl()).build();
        return loader.load();
    }

    public void fromConfig(ConfigurationNode skillConfig) throws ObjectMappingException {

        skillName = skillConfig.getNode("Name").getString("Unknown");
        skillDescription = skillConfig.getNode("Description").getString("Unknown");

        experienceMap = skillConfig.getNode("ExpMap").getValue(new TypeToken<Map<String, Integer>>() {
        }, new HashMap<>());
    }
}
