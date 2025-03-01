package com.hibiscusmc.hmccosmetics.gui.type.types;

import com.hibiscusmc.hmccosmetics.gui.action.Actions;
import com.hibiscusmc.hmccosmetics.gui.type.Type;
import com.hibiscusmc.hmccosmetics.hooks.Hooks;
import com.hibiscusmc.hmccosmetics.hooks.placeholders.HMCPlaceholderExpansion;
import com.hibiscusmc.hmccosmetics.user.CosmeticUser;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.List;

public class TypeEmpty extends Type {

    // This can be used as an example for making your own types.

    public TypeEmpty() {
        super("empty");
        // This is an empty type, meaning, when a menu item has a type of "empty" it will run the code in the method run.
    }

    // This is the code that's run when the item is clicked.
    @Override
    public void run(CosmeticUser user, ConfigurationNode config, ClickType clickType) {
        List<String> actionStrings = new ArrayList<>(); // List where we keep the actions the server will execute.
        ConfigurationNode actionConfig = config.node("actions"); // Configuration node that actions are under.

        // We have to encase it in try catch for configurate serialization
        try {
            // This gets the actions with the item. We can add more, such as with the Cosmetic type, an equip and unequip action set.
            // We add that to a List of Strings, before running those actions through the server. This is not the area where we deal
            // with actions, mearly what should be done for each item.
            if (!actionConfig.node("any").virtual()) actionStrings.addAll(actionConfig.node("any").getList(String.class));

            if (clickType != null) {
                if (clickType.isLeftClick()) {
                    if (!actionConfig.node("left-click").virtual()) actionStrings.addAll(actionConfig.node("left-click").getList(String.class));
                }
                if (clickType.isRightClick()) {
                    if (!actionConfig.node("right-click").virtual()) actionStrings.addAll(actionConfig.node("right-click").getList(String.class));
                }
            }

            // We run the actions once we got the raw strings from the config.
            Actions.runActions(user, actionStrings);
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemMeta setLore(CosmeticUser user, ConfigurationNode config, ItemMeta itemMeta) {
        List<String> processedLore = new ArrayList<>();

        if (itemMeta.hasLore()) {
            for (String loreLine : itemMeta.getLore()) {
                if (Hooks.isActiveHook("PlaceholderAPI"))
                    loreLine = PlaceholderAPI.setPlaceholders(user.getPlayer(), loreLine);
                processedLore.add(loreLine);
            }
        }

        return itemMeta;
    }

    // That's it! Now, add it as a static in another one of your classes (such as your main class) and you are good to go.
    // If you need help with that, check the Types class.
}
