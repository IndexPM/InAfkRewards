package com.index.inafkrewards.managers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AfkRegionManager {

    private final Location afkLocation;
    private final double radius;
    private final JavaPlugin plugin;

    public AfkRegionManager(JavaPlugin plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();

        try {
            double x = config.getDouble("afk-region.x", 0.0);
            double y = config.getDouble("afk-region.y", 0.0);
            double z = config.getDouble("afk-region.z", 0.0);
            String worldName = config.getString("afk-region.world", "world");
            this.radius = config.getDouble("afk-region.radius", 5.0);

            World world = plugin.getServer().getWorld(worldName);
            if (world == null) {
                plugin.getLogger().warning("⚠ Mundo '" + worldName + "' no encontrado. Usando mundo por defecto.");
                world = plugin.getServer().getWorlds().get(0);
            }

            this.afkLocation = new Location(world, x, y, z);
            plugin.getLogger().info("Región AFK configurada: " + worldName + " (" + x + ", " + y + ", " + z + ") con radio " + radius);
        } catch (Exception e) {
            plugin.getLogger().severe("Error al cargar la configuración de la región AFK: " + e.getMessage());
            throw new RuntimeException("Error inicializando AfkRegionManager", e);
        }
    }

    public boolean isPlayerInAfkRegion(Location playerLocation) {
        if (playerLocation == null || afkLocation == null) {
            return false;
        }
        
        if (!playerLocation.getWorld().equals(afkLocation.getWorld())) {
            return false;
        }
        
        return playerLocation.distance(afkLocation) <= radius;
    }

    public Location getAfkLocation() {
        return afkLocation;
    }

    public double getRadius() {
        return radius;
    }
}
