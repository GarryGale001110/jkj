package com.hibiscusmc.hmccosmetics.hooks.placeholders;

import com.hibiscusmc.hmccosmetics.hooks.Hook;

public class HookPlaceholderAPI extends Hook {

    public HookPlaceholderAPI() {
        super("placeholderapi");
    }

    @Override
    public void load() {
        new HMCPlaceholderExpansion().register();
    }
}
