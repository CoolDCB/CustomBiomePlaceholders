package me.dave.custombiomeplaceholders;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomBiomePlaceholders extends JavaPlugin {
    private PlaceholderAPIHook placeholderAPIHook = null;

    @Override
    public void onEnable() {
        placeholderAPIHook = new PlaceholderAPIHook(this);
        placeholderAPIHook.register();
    }

    @Override
    public void onDisable() {
        if (placeholderAPIHook != null) {
            placeholderAPIHook.unregister();
            placeholderAPIHook = null;
        }
    }
}
