package com.index.inafkrewards;

import com.index.inafkrewards.commands.CommandHandler;
import com.index.inafkrewards.listeners.PlayerAFKListener;
import com.index.inafkrewards.managers.AfkRegionManager;
import com.index.inafkrewards.managers.RewardHandler;
import com.index.inafkrewards.placeholders.InAfkRewardsPlaceholder;
import org.bukkit.plugin.java.JavaPlugin;

public class InAfkRewardsPlugin extends JavaPlugin {
    
    private static InAfkRewardsPlugin instance;
    private AfkRegionManager afkRegionManager;
    private RewardHandler rewardHandler;

    @Override
    public void onEnable() {
        instance = this;
        
        saveDefaultConfig();
        getLogger().info("═══════════════════════════════════════");
        getLogger().info("  InAfkRewards v" + getDescription().getVersion());
        getLogger().info("  Autor: " + getDescription().getAuthor());
        getLogger().info("═══════════════════════════════════════");
        
        try {
            this.afkRegionManager = new AfkRegionManager(this);
            getLogger().info("✓ AfkRegionManager inicializado");
            
            this.rewardHandler = new RewardHandler(this);
            getLogger().info("✓ RewardHandler inicializado");
        } catch (Exception e) {
            getLogger().severe("✗ Error al inicializar managers: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        getServer().getPluginManager().registerEvents(new PlayerAFKListener(this), this);
        getLogger().info("✓ PlayerAFKListener registrado");
        
        new CommandHandler(this);
        getLogger().info("✓ Comandos registrados");
        
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new InAfkRewardsPlaceholder(this).register();
            getLogger().info("✓ PlaceholderAPI hook registrado");
        } else {
            getLogger().warning("⚠ PlaceholderAPI no encontrado - placeholders deshabilitados");
        }
        
        getLogger().info("✓ Plugin habilitado correctamente");
    }

    @Override
    public void onDisable() {
        getLogger().info("✗ Plugin deshabilitado");
        if (rewardHandler != null) {
            rewardHandler.shutdown();
        }
        instance = null;
    }

    public static InAfkRewardsPlugin getInstance() {
        return instance;
    }

    public AfkRegionManager getAfkRegionManager() {
        return afkRegionManager;
    }

    public RewardHandler getRewardHandler() {
        return rewardHandler;
    }
}
