package com.index.inafkrewards.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class RewardHandler {

    private final JavaPlugin plugin;
    private final long rewardInterval;
    private final String rewardCommand;
    private final Map<Player, Long> lastRewardTime = new HashMap<>();
    private BukkitTask rewardTask;

    public RewardHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.rewardInterval = plugin.getConfig().getLong("reward.interval", 300) * 20L; 
        this.rewardCommand = plugin.getConfig().getString("reward.command", "give %player_name% diamond 1");
        
        plugin.getLogger().info("RewardHandler configurado - Intervalo: " + (rewardInterval / 20) + "s");
        plugin.getLogger().info("Comando de recompensa: " + rewardCommand);
    }

    public void giveReward(Player player) {
        if (player == null || !player.isOnline()) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        Long lastReward = lastRewardTime.getOrDefault(player, 0L);

        if (currentTime - lastReward < rewardInterval * 50) { // 50ms por tick
            return;
        }

        lastRewardTime.put(player, currentTime);

        String command = rewardCommand.replace("%player_name%", player.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        
        player.sendMessage("§a[§6InAfkRewards§a] ¡Has recibido una recompensa!");
        plugin.getLogger().info("Recompensa otorgada a " + player.getName() + ": " + command);
    }

    public long getRewardInterval() {
        return rewardInterval;
    }

    public void shutdown() {
        if (rewardTask != null) {
            rewardTask.cancel();
        }
        lastRewardTime.clear();
    }
}
