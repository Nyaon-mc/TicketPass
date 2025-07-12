package com.github.nyaon08.siny.inviteplayer.listener;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import com.github.nyaon08.siny.inviteplayer.configuration.NameTagConfig;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import kr.rtuserver.framework.bukkit.api.registry.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemInteractEvent extends RSListener<InvitePlayer> {

    private final NameTagConfig config;

    public ItemInteractEvent(InvitePlayer plugin) {
        super(plugin);
        this.config = plugin.getNameTagConfig();
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        if (!List.of(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(e.getAction())) return;
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        if (itemStack != null) {
            if (CustomItems.to(itemStack).equalsIgnoreCase(config.getNameTagItem())) {
                new InvitePlayerEvent(getPlugin(), player).open();
            }
        }
    }

}
