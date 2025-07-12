package com.github.nyaon08.siny.inviteplayer.manager;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import com.google.gson.JsonObject;
import kr.rtuserver.framework.bukkit.api.platform.JSON;
import kr.rtuserver.framework.bukkit.api.storage.Storage;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class InvitePlayerManager {

    private final InvitePlayer plugin;

    public void add(UUID invitee, UUID inviter) {
        Storage storage = plugin.getStorage();
        storage.add("ticket", JSON.of("invitee", invitee.toString())
                .append("inviter", inviter.toString())
        ).join();
    }

    public boolean isInvited(UUID uuid) {
        Storage storage = plugin.getStorage();
        List<JsonObject> list = storage.get("ticket", JSON.of("invitee", uuid.toString())).join();
        return !list.isEmpty();
    }

}
