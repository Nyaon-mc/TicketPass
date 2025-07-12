package com.github.nyaon08.siny.inviteplayer.configuration;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import kr.rtuserver.framework.bukkit.api.configuration.RSConfiguration;
import lombok.Getter;

import java.util.List;

@Getter
public class NameTagConfig extends RSConfiguration<InvitePlayer> {

    private String kickMessage = "<red>서버에 접속할 권한이 없습니다. 초대를 받아야 접속할 수 있습니다.</red>";

    private List<String> allowedPlayers = List.of();

    private String nameTagItem = "minecraft:paper";
    private String invitePlayerInventoryName = "초대권";

    public NameTagConfig(InvitePlayer plugin) {
        super(plugin, "invitePlayer.yml", 1);
        setup(this);
    }

    private void init() {
        kickMessage = getString("kickMessage", kickMessage, """
                초대되지 않는 유저가 입장 시 표시될 메세지""");

        allowedPlayers = getStringList("allowedPlayers", allowedPlayers, """
                초대가 가능한 유저 리스트
                ex) player1, player2, player3""");

        nameTagItem = getString("inviteItem", nameTagItem, """
                초대권으로 설정할 아이템
                ex) namespace:id""");

        invitePlayerInventoryName = getString("invitePlayerInventoryName", invitePlayerInventoryName, """
                초대권 인벤토리의 이름""");
    }

}
