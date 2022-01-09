package best.boba.bobacars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CompleterCarsHandler implements TabCompleter {
    private final Config config;
    public CompleterCarsHandler(final Config config) {
        this.config = config;
    }

    // https://bukkit.org/threads/tutorial-sub-commands-in-different-classes.203341/

    private static final HashMap<String, CompleterCarsInterface> subcompleters = new HashMap<>();

    public void register(String name, CompleterCarsInterface command) {
        subcompleters.put(name, command);
    }

    public boolean exists(String name) {
        return subcompleters.containsKey(name);
    }

    public CompleterCarsInterface getCompleter(String name) {
        return subcompleters.get(name);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        String[] subcompleterArgs;
        if (args.length > 1) {
            subcompleterArgs = Arrays.copyOfRange(args, 1, args.length);
        } else {
            subcompleterArgs = new String[0];
        }

        if (args.length == 1) {
            return new ArrayList<>(subcompleters.keySet());
        }

        if (exists(args[0])) {
            return getCompleter(args[0]).onTabComplete(sender, command, alias, subcompleterArgs);
        }

        return new ArrayList<>();
    }
}
