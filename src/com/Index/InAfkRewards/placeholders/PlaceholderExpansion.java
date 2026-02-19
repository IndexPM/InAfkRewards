package com.index.inafkrewards.placeholders;

import com.index.inafkrewards.InAfkRewardsPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InAfkRewardsPlaceholder extends PlaceholderExpansion {

    private final InAfkRewardsPlugin plugin;

    public InAfkRewardsPlaceholder(InAfkRewardsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "inafkrewards";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().isEmpty() ? "IndexDev" : plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }

        // %inafkrewards_inline%
        if (params.equalsIgnoreCase("inafk")) {
            if (plugin.getAfkRegionManager().isPlayerInAfkRegion(player.getLocation())) {
                return "§a✓ En región AFK";
            } else {
                return "§c✗ Fuera de región AFK";
            }
        }

        // %inafkrewards_radius%
        if (params.equalsIgnoreCase("radius")) {
            return String.valueOf(plugin.getAfkRegionManager().getRadius());
        }

        // %inafkrewards_location%
        if (params.equalsIgnoreCase("location")) {
            return "§6" + plugin.getAfkRegionManager().getAfkLocation().getBlockX() + ", " +
                    plugin.getAfkRegionManager().getAfkLocation().getBlockY() + ", " +
                    plugin.getAfkRegionManager().getAfkLocation().getBlockZ() + " §8(" +
                    plugin.getAfkRegionManager().getAfkLocation().getWorld().getName() + ")";
        }

        return null;
    }
}
