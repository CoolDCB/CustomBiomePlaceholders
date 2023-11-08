package me.dave.custombiomeplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {
    private final CustomBiomePlaceholders plugin;

    public PlaceholderAPIHook(CustomBiomePlaceholders plugin) {
        this.plugin = plugin;
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player != null) {
            if (params.equals("biome")) {
                return getBiomeName(player.getLocation());
            }
        }

        String[] args = params.split("_");
        if (args.length == 5) {
            if (args[0].equals("biome")) {
                World world = Bukkit.getWorld(args[1]);
                if (world == null) {
                    return null;
                }

                Location location;
                try {
                    int x = Integer.parseInt(args[2]);
                    int y = Integer.parseInt(args[3]);
                    int z = Integer.parseInt(args[4]);
                    location = new Location(world, x, y, z);
                } catch (NumberFormatException e) {
                    return null;
                }

                return getBiomeName(location);
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

    private static String getBiomeName(Location location) {
        NamespacedKey key = Bukkit.getUnsafe().getBiomeKey(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return key.getKey().toUpperCase();
    }
}
