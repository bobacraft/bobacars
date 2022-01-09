package best.boba.bobacars.completers;

import best.boba.bobacars.CompleterCarsInterface;
import best.boba.bobacars.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompleterCarsHelp implements CompleterCarsInterface {
    private Config config;
    public CompleterCarsHelp(final Config config) {
        this.config = config;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
