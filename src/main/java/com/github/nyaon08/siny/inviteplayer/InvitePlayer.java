package com.github.nyaon08.siny.inviteplayer;

import com.github.nyaon08.siny.inviteplayer.command.MainCommand;
import com.github.nyaon08.siny.inviteplayer.configuration.NameTagConfig;
import com.github.nyaon08.siny.inviteplayer.listener.ItemInteractEvent;
import com.github.nyaon08.siny.inviteplayer.listener.PlayerJoinEvent;
import com.github.nyaon08.siny.inviteplayer.manager.InvitePlayerManager;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.Getter;

public class InvitePlayer extends RSPlugin {

    @Getter
    private static InvitePlayer instance;

    @Getter
    private NameTagConfig nameTagConfig;

    @Getter
    private InvitePlayerManager invitePlayerManager;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        getConfigurations().getStorage().init("ticket");

        nameTagConfig = new NameTagConfig(this);
        invitePlayerManager = new InvitePlayerManager(this);

        registerCommand(new MainCommand(this), true);

        registerEvent(new ItemInteractEvent(this));
        registerEvent(new PlayerJoinEvent(this));
    }

    @Override
    public void disable() {
        instance = null;
    }

}
