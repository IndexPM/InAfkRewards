package com.index.inafkrewards.commands;

import com.index.inafkrewards.InAfkRewardsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandHandler implements CommandExecutor {

    private final InAfkRewardsPlugin plugin;

    public CommandHandler(InAfkRewardsPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("afkrewards").setExecutor(this);
        plugin.getCommand("afkrewardsreload").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("afkrewards")) {
            return handleAfkRewardsCommand(sender, args);
        } else if (command.getName().equalsIgnoreCase("afkrewardsreload")) {
            return handleReloadCommand(sender);
        }
        return false;
    }

    private boolean handleAfkRewardsCommand(CommandSender sender, String[] args) {
        sender.sendMessage("");
        sender.sendMessage("§6╔═══════════════════════════════════════╗");
        sender.sendMessage("§6║ §bInAfkRewards v" + plugin.getDescription().getVersion() + " §6║");
        sender.sendMessage("§6╚═══════════════════════════════════════╝");
        sender.sendMessage("");
        sender.sendMessage("§eInformación del Plugin:");
        sender.sendMessage("§7Autor: §f" + plugin.getDescription().getAuthor());
        sender.sendMessage("§7Descripción: §f" + plugin.getDescription().getDescription());
        sender.sendMessage("");
        sender.sendMessage("§eConfiguración Actual:");
        sender.sendMessage("§7Región: §f" + plugin.getConfig().getString("afk-region.world") +
                " (" + plugin.getConfig().getDouble("afk-region.x") + ", " +
                plugin.getConfig().getDouble("afk-region.y") + ", " +
                plugin.getConfig().getDouble("afk-region.z") + ")");
        sender.sendMessage("§7Radio: §f" + plugin.getConfig().getDouble("afk-region.radius") + " bloques");
        sender.sendMessage("§7Intervalo de Recompensa: §f" + plugin.getConfig().getLong("reward.interval") + " segundos");
        sender.sendMessage("§7Comando: §f" + plugin.getConfig().getString("reward.command"));
        sender.sendMessage("");
        sender.sendMessage("§eComandos:");
        sender.sendMessage("§7/afkrewards §f- Muestra esta información");
        sender.sendMessage("§7/afkrewardsreload §f- Recarga la configuración");
        sender.sendMessage("");
        return true;
    }

    private boolean handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("inafkrewards.reload") && !sender.hasPermission("inafkrewards.*")) {
            sender.sendMessage("§c✗ No tienes permiso para usar este comando");
            return true;
        }

        try {
            plugin.reloadConfig();
            sender.sendMessage("§a✓ Configuración recargada correctamente");
            plugin.getLogger().info("Configuración recargada por " + sender.getName());
        } catch (Exception e) {
            sender.sendMessage("§c✗ Error al recargar la configuración: " + e.getMessage());
            plugin.getLogger().severe("Error al recargar configuración: " + e.getMessage());
        }
        return true;
    }
}
