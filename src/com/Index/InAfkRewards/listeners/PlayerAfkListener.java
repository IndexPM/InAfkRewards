package com.index.inafkrewards.listeners;

import com.index.inafkrewards.InAfkRewardsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerAFKListener implements Listener {

    private final InAfkRewardsPlugin plugin;

    public PlayerAFKListener(InAfkRewardsPlugin plugin) {
        this.plugin = plugin;
        startRewardTask();
    }

    private void startRewardTask() {
        long rewardInterval = plugin.getRewardHandler().getRewardInterval();
        
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (plugin.getAfkRegionManager().isPlayerInAfkRegion(player.getLocation())) {
                        plugin.getRewardHandler().giveReward(player);
                    }
                }
            }
        }.runTaskTimer(plugin, rewardInterval, rewardInterval);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getLogger().info("Jugador conectado: " + player.getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getLogger().info("Jugador desconectado: " + player.getName());
    }
}
