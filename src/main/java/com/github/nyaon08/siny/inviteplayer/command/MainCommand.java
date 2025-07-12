package com.github.nyaon08.siny.inviteplayer.command;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import com.github.nyaon08.siny.inviteplayer.configuration.NameTagConfig;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;

import java.util.List;

public class MainCommand extends RSCommand<InvitePlayer> {

    private final NameTagConfig nameTagConfig;

    public MainCommand(InvitePlayer plugin) {
        super(plugin, "inviteplayer");
        this.nameTagConfig = plugin.getNameTagConfig();
    }

    @Override
    protected boolean execute(RSCommandData data) {
        return true;
    }

    @Override
    protected List<String> tabComplete(RSCommandData data) {
        return List.of();
    }

    @Override
    protected void reload(RSCommandData data) {
        nameTagConfig.reload();
    }

}
