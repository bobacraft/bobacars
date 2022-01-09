package best.boba.bobacars.completers;

import best.boba.bobacars.CompleterCarsInterface;
import best.boba.bobacars.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompleterCarsSpawn implements CompleterCarsInterface {
    private final Config config;
    public CompleterCarsSpawn(final Config config) {
        this.config = config;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completion = new ArrayList<>();
        switch (args.length) {
            case 1 -> completion.add("one");
            case 2 -> completion.add("two");
        }
        return completion;
    }
}
