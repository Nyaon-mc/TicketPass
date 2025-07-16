package com.github.nyaon08.siny.inviteplayer.listener;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import com.github.nyaon08.siny.inviteplayer.configuration.NameTagConfig;
import com.github.nyaon08.siny.inviteplayer.manager.InvitePlayerManager;
import kr.rtuserver.framework.bukkit.api.format.ComponentFormatter;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerJoinEvent extends RSListener<InvitePlayer> {

    private final NameTagConfig nameTagConfig;
    private final InvitePlayerManager manager;

    public PlayerJoinEvent(InvitePlayer plugin) {
        super(plugin);
        this.nameTagConfig = plugin.getNameTagConfig();
        this.manager = plugin.getInvitePlayerManager();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(AsyncPlayerPreLoginEvent e) {
        if (nameTagConfig.getBypassPlayers().contains(e.getName())) return;
        if (!manager.isInvited(e.getUniqueId())) {
            Component message = ComponentFormatter.parse(nameTagConfig.getKickMessage());
            e.kickMessage(message);
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, message);
        }
    }

}
