package me.dave.custombiomeplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {
    private final CustomBiomePlaceholders plugin;

    public PlaceholderAPIHook(CustomBiomePlaceholders plugin) {
        this.plugin = plugin;
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player != null) {
            switch (params) {
                case "biome_formatted" -> {
                    NamespacedKey biomeKey = getBiomeNamespacedKey(player.getLocation());
                    return format(biomeKey.getKey()) + ": " + format(biomeKey.getNamespace());
                }
                case "biome_name" -> {
                    return format(getBiomeNamespacedKey(player.getLocation()).getNamespace());
                }
                case "biome_namespace" -> {
                    return format(getBiomeNamespacedKey(player.getLocation()).getKey());
                }
                case "biome_raw" -> {
                    return getBiomeNamespacedKey(player.getLocation()).asString();
                }
            }
        }

        return null;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public @NotNull String getIdentifier() {
        return "cbp";
    }

    public @NotNull String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    public @NotNull String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    private static NamespacedKey getBiomeNamespacedKey(Location location) {
        return Bukkit.getUnsafe().getBiomeKey(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    private String format(String string) {
        String[] words = string.split(" ");

        StringBuilder stringBuilder = new StringBuilder(string);
        for (String word : words) {
            stringBuilder.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
        }

        return stringBuilder.toString();
    }
}
