package com.github.julyss2019.mcsp.julyguild.gui.player;

import com.github.julyss2019.mcsp.julyguild.JulyGuild;
import com.github.julyss2019.mcsp.julyguild.config.ConfigGuildIcon;
import com.github.julyss2019.mcsp.julyguild.config.IconShopConfig;
import com.github.julyss2019.mcsp.julyguild.gui.BasePageableGUI;
import com.github.julyss2019.mcsp.julyguild.gui.GUIType;
import com.github.julyss2019.mcsp.julyguild.guild.Guild;
import com.github.julyss2019.mcsp.julyguild.player.GuildPlayer;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GuildIconShopGUI extends BasePageableGUI {
    private Inventory inventory;
    private static JulyGuild plugin = JulyGuild.getInstance();
    private static IconShopConfig iconShopConfig = plugin.getIconShopConfig();

    private List<ConfigGuildIcon> icons = new ArrayList<>();
    private Guild guild;

    public GuildIconShopGUI(GuildPlayer guildPlayer) {
        super(GUIType.ICON_SHOP, guildPlayer);

        this.guild = this.guildPlayer.getGuild();
        build();
    }

    @Override
    public void setCurrentPage(int page) {
        super.setCurrentPage(page);
/*
        icons.clear();
        icons.addAll(iconShopConfig.getIcons());

        InventoryBuilder inventoryBuilder = new InventoryBuilder().title(ConfigHandler.getString("GuildIconShopGUI.title")).row(6).colored().listener(new InventoryListener() {
            @Override
            public void onClicked(InventoryClickEvent event) {
                int index = event.getSlot() + getCurrentPage() * 51;

                if (index < icons.size()) {
                    ConfigGuildIcon configGuildIcon = icons.get(index);

                    if (guild.isOwnedIcon(configGuildIcon.getMaterial(), configGuildIcon.getDurability())) {
                        Util.sendColoredMessage(bukkitPlayer, ConfigHandler.getString("GuildIconShopGUI.already_own"));
                        return;
                    }

                    close();
                    new GuildIconBuyGUI(guildPlayer, configGuildIcon).open();
                }
            }
        });

        inventoryBuilder
                .item(53, CommonItem.BACK, new ItemListener() {
            @Override
            public void onClicked(InventoryClickEvent event) {
                close();
                new GuildManageGUI(guildPlayer).open();
            }
        });

        if (getTotalPage() > 1) {
            inventoryBuilder.item(51, CommonItem.PREVIOUS_PAGE, new ItemListener() {
                @Override
                public void onClicked(InventoryClickEvent event) {
                    if (hasPrecious()) {
                        close();
                        previousPage();
                    }
                }
            });
            inventoryBuilder.item(52, CommonItem.NEXT_PAGE, new ItemListener() {
                @Override
                public void onClicked(InventoryClickEvent event) {
                    if (hasNext()) {
                        close();
                        nextPage();
                    }
                }
            });
        }

        int iconSize = icons.size();
        int itemCounter = page * 51;
        int loopCount = iconSize - itemCounter < 51 ? iconSize - itemCounter : 51;

        for (int i = 0; i < loopCount; i++) {
            ConfigGuildIcon icon = icons.get(itemCounter++);
            boolean isOwned = guild.isOwnedIcon(icon.getMaterial(), icon.getDurability());

            ItemBuilder itemBuilder = new ItemBuilder()
                    .material(icon.getMaterial())
                    .durability(icon.getDurability())
                    .displayName(icon.getDisplayName())
                    .addLore(isOwned ? ConfigHandler.getString("GuildIconShopGUI.icon.owned") : ConfigHandler.getString("GuildIconShopGUI.icon.buy"))
                    .addLore("")
                    .addLores(ListUtil.replacePlaceholders(icon.getLores(),
                            new MapBuilder<String, String>().put("%money%", String.valueOf(icon.getMoneyCost())).put("%points%", String.valueOf(icon.getPointsCost())).build()))
                    .colored();

            if (isOwned) {
                itemBuilder.enchant(Enchantment.DURABILITY, 1);
                itemBuilder.addItemFlag(ItemFlag.HIDE_ENCHANTS);
            }

            inventoryBuilder.item(i, itemBuilder.build());
        }

        this.inventory = inventoryBuilder.build();*/
    }

    @Override
    public int getTotalPage() {
        int iconSize = icons.size();

        return iconSize == 0 ? 1 : iconSize % 51 == 0 ? iconSize / 51 : iconSize / 51 + 1;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}