package com.hibiscusmc.hmccosmetics.cosmetic.types;

import com.hibiscusmc.hmccosmetics.cosmetic.Cosmetic;
import com.hibiscusmc.hmccosmetics.user.CosmeticUser;
import com.hibiscusmc.hmccosmetics.util.packets.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;

public class CosmeticBackpackType extends Cosmetic {

    ConfigurationNode config;

    public CosmeticBackpackType(String id, ConfigurationNode config) {
        super(id, config);

        this.config = config;
    }

    @Override
    public void update(CosmeticUser user) {
        Player player = Bukkit.getPlayer(user.getUniqueId());
        Location loc = player.getLocation().clone();

        if (user.isInWardrobe()) return;

        if (loc.getWorld() != user.getUserBackpackManager().getArmorstand().getWorld()) {
            user.getUserBackpackManager().getArmorstand().teleport(loc);
        }

        user.getUserBackpackManager().getArmorstand().teleport(loc);

        PacketManager.sendRidingPacket(player.getEntityId(), user.getUserBackpackManager().getFirstArmorstandId(), loc);

        user.getUserBackpackManager().getArmorstand().setRotation(loc.getYaw(), loc.getPitch());
        user.getUserBackpackManager().showBackpack();
    }
}
