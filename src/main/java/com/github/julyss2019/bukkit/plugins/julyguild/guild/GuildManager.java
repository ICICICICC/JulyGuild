package com.github.julyss2019.bukkit.plugins.julyguild.guild;

import com.github.julyss2019.bukkit.plugins.julyguild.JulyGuild;
import com.github.julyss2019.bukkit.plugins.julyguild.api.event.GuildCreateEvent;
import com.github.julyss2019.bukkit.plugins.julyguild.log.guild.GuildCreateLog;
import com.github.julyss2019.bukkit.plugins.julyguild.player.GuildPlayer;
import com.github.julyss2019.mcsp.julylibrary.utils.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class GuildManager {
    private final JulyGuild plugin = JulyGuild.getInstance();
    private final Map<UUID, Guild> guildMap = new HashMap<>();

    public GuildManager() {}

    /**
     * 创建公会
     * @param ownerPlayer 公会主人
     * @return
     */
    public void createGuild(GuildPlayer ownerPlayer, @NotNull String guildName) {
        if (ownerPlayer.isInGuild()) {
            throw new IllegalArgumentException("主人已经有公会了");
        }

        UUID uuid = UUID.randomUUID();
        File file = new File(plugin.getDataFolder(), "data" + File.separator + "guilds" + File.separator + uuid + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

        yml.set("uuid", uuid.toString());
        yml.set("name", guildName);
        yml.set("creation_time", System.currentTimeMillis());
        yml.set("members." + ownerPlayer.getUuid() + ".position", GuildPosition.OWNER.name());
        yml.set("members." + ownerPlayer.getUuid() + ".join_time", System.currentTimeMillis());

        YamlUtil.saveYaml(yml, file, StandardCharsets.UTF_8);

        loadGuild(file); // 载入公会
        // 触发 Bukkit 事件
        Bukkit.getPluginManager().callEvent(new GuildCreateEvent(getGuild(uuid), ownerPlayer));
        plugin.writeGuildLog(new GuildCreateLog(uuid, guildName, ownerPlayer.getName()));
    }

    public int getGuildCount() {
        return guildMap.size();
    }

    public Collection<Guild> getGuilds() {
        return guildMap.values();
    }

    /**
     * 得到宗门列表
     * @return
     */
    public List<Guild> getSortedGuilds() {
        return new ArrayList<>(guildMap.values()).stream().sorted((o1, o2) -> o1.getRank() > o2.getRank() ? -1 : 0).collect(Collectors.toList());
    }

    public boolean isLoaded(@NotNull UUID uuid) {
        return guildMap.containsKey(uuid);
    }

    public boolean isValid(@Nullable Guild guild) {
        return guild != null && isLoaded(guild.getUuid());
    }

    /**
     * 卸载公会
     * @param guild
     */
    public void unloadGuild(@NotNull Guild guild) {
        if (!isLoaded(guild.getUuid())) {
            throw new RuntimeException("公会未载入");
        }

        guildMap.remove(guild.getUuid());
        guild.setValid(false);
        JulyGuild.getInstance().getCacheGuildManager().updateSortedGuilds();
    }

    /**
     * 载入公会
     * @param file
     */
    public void loadGuild(@NotNull File file) {
        Guild guild = new Guild(file);

        // 被删除
        if (guild.isDeleted()) {
            return;
        }

        if (isLoaded(guild.getUuid())) {
            throw new RuntimeException("公会已载入");
        }

        guildMap.put(guild.getUuid(), guild);
        JulyGuild.getInstance().getCacheGuildManager().updateSortedGuilds();
    }

    /**
     * 载入所有公会
     */
    public void loadGuilds() {
        guildMap.clear();

        File guildFolder = new File(plugin.getDataFolder(), "data" + File.separator + "guilds");

        if (!guildFolder.exists()) {
            return;
        }

        File[] guildFiles = guildFolder.listFiles();

        if (guildFiles != null) {
            for (File guildFile : guildFiles) {
                loadGuild(guildFile);
            }
        }
    }

    public Guild getGuild(@NotNull UUID uuid) {
        return guildMap.get(uuid);
    }

    public void unloadAll() {
        for (Guild guild : getGuilds()) {
            unloadGuild(guild);
        }
    }
}