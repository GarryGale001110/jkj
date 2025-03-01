package com.hibiscusmc.hmccosmetics.gui.action.actions;

import com.hibiscusmc.hmccosmetics.gui.action.Action;
import com.hibiscusmc.hmccosmetics.user.CosmeticUser;
import com.hibiscusmc.hmccosmetics.util.MessagesUtil;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ActionSound extends Action {
    // [SOUND] minecraft:test 1 1

    public ActionSound() {
        super("sound");
    }

    @Override
    public void run(CosmeticUser user, String raw) {
        Player player = user.getPlayer();
        String[] processedString = raw.split(" ");

        String soundName = processedString[0];
        float volume = 1;
        float pitch = 1;

        if (processedString.length > 2) {
            volume = Float.valueOf(processedString[1]);
            pitch = Float.valueOf(processedString[2]);
        }

        MessagesUtil.sendDebugMessages("Attempting to play " + soundName, Level.WARNING);

        player.playSound(player.getLocation(), soundName, volume, pitch);
    }
}
