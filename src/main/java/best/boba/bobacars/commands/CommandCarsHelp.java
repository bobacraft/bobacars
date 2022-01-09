package best.boba.bobacars.commands;

import best.boba.bobacars.CommandCarsInterface;
import best.boba.bobacars.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandCarsHelp implements CommandCarsInterface {
    private final Config config;
    public CommandCarsHelp(final Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("Good luck.");
        return true;
    }
}
