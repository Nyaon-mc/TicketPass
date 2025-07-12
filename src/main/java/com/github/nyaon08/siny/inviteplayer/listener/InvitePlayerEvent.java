package com.github.nyaon08.siny.inviteplayer.listener;

import com.github.nyaon08.siny.inviteplayer.InvitePlayer;
import com.github.nyaon08.siny.inviteplayer.configuration.NameTagConfig;
import com.github.nyaon08.siny.inviteplayer.manager.InvitePlayerManager;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class InvitePlayerEvent extends RSListener<InvitePlayer> {

    private final NameTagConfig nameTagConfig;
    private final InvitePlayerManager manager;

    private final Player player;

    public InvitePlayerEvent(InvitePlayer plugin, Player player) {
        super(plugin);
        this.nameTagConfig = plugin.getNameTagConfig();
        this.manager = plugin.getInvitePlayerManager();
        this.player = player;
    }

    public void open() {
        new AnvilGUI.Builder()
                .plugin(getPlugin())
                .title(nameTagConfig.getInvitePlayerInventoryName())
                .text(message().get(player, "invite.input_prompt"))
                .itemLeft(new ItemStack(Material.PAPER))
                .onClick(this::handleAnvilClick)
                .open(player);
    }

    private List<AnvilGUI.ResponseAction> handleAnvilClick(Integer slot, AnvilGUI.StateSnapshot state) {
        if (slot != AnvilGUI.Slot.OUTPUT) {
            return Collections.emptyList();
        }

        String input = state.getText().trim();
        Player sender = state.getPlayer();

        if (input.isEmpty()) {
            chat().announce(player, message().get(player, "invite.input_required"));
            return List.of(AnvilGUI.ResponseAction.close());
        }

        if (!Pattern.compile("^[a-zA-Z0-9_]{3,16}$").matcher(input).matches()) {
            chat().announce(player, message().get(player, "invite.invalid_nickname_format"));
            return List.of(AnvilGUI.ResponseAction.close());
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(input);

        invitePlayer(target, sender);
        return List.of(AnvilGUI.ResponseAction.close());
    }

    private void invitePlayer(OfflinePlayer target, Player sender) {
        if (manager.isInvited(target.getUniqueId())) {
            chat().announce(sender, message().get(sender, "invite.already_invited"));
            return;
        }

        manager.add(target.getUniqueId(), sender.getUniqueId());
        chat().announce(sender, message().get(sender, "invite.success")
                .replace("[invitee]", target.getName())
        );

        ItemStack item = sender.getInventory().getItemInMainHand();
        item.setAmount(item.getAmount() - 1);
    }
}
