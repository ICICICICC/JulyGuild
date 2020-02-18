package com.github.julyss2019.bukkit.plugins.julyguild.player;

import com.github.julyss2019.bukkit.plugins.julyguild.JulyGuild;
import com.github.julyss2019.mcsp.julylibrary.utils.YamlUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class GuildPlayerManager {
    private JulyGuild plugin = JulyGuild.getInstance();
    private Map<UUID, GuildPlayer> guildPlayerMap = new HashMap<>();

    public GuildPlayer getGuildPlayer(UUID uuid) {
        if (!guildPlayerMap.containsKey(uuid)) {
            guildPlayerMap.put(uuid, isRegistered(uuid) ? new GuildPlayer(getGuildPlayerFile(uuid)) : registerGuildPlayer(uuid));
        }


        return guildPlayerMap.get(uuid);
    }

    public GuildPlayer registerGuildPlayer(UUID uuid) {
        if (isRegistered(uuid)) {
            throw new IllegalArgumentException("该玩家已注册 GuildPlayer");
        }

        File file = getGuildPlayerFile(uuid);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

        yml.set("uuid", uuid.toString());
        yml.set("register_time", System.currentTimeMillis());
        YamlUtil.saveYaml(yml, file);
        return new GuildPlayer(file);
    }

    public boolean isRegistered(Player player) {
        return isRegistered(player.getUniqueId());
    }

    public boolean isRegistered(UUID uuid) {
        return getGuildPlayerFile(uuid).exists();
    }

    private File getGuildPlayerFile(UUID uuid) {
        return new File(plugin.getDataFolder(), "data" + File.separator + "players" + File.separator + uuid + ".yml");
    }

    public GuildPlayer getGuildPlayer(Player player) {
        return getGuildPlayer(player.getUniqueId());
    }

    public Collection<GuildPlayer> getOnlineGuildPlayers() {
        return guildPlayerMap.size() == 0 ? new ArrayList<>() : guildPlayerMap.values().stream().filter(GuildPlayer::isOnline).collect(Collectors.toList());
    }

    public Collection<GuildPlayer> getLoadedGuildPlayers() {
        return guildPlayerMap.values();
    }
}