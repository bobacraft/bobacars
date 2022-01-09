package best.boba.bobacars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class CommandCarsHandler implements CommandExecutor {
    private final Config config;
    public CommandCarsHandler(final Config config) {
        this.config = config;
    }

    // https://bukkit.org/threads/tutorial-sub-commands-in-different-classes.203341/

    private static final HashMap<String, CommandCarsInterface> subcommands = new HashMap<>();

    public void register(String name, CommandCarsInterface command) {
        subcommands.put(name, command);
    }

    public boolean exists(String name) {
        return subcommands.containsKey(name);
    }

    public CommandCarsInterface getExecutor(String name) {
        return subcommands.get(name);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String[] subcommandArgs;
        if (args.length > 1) {
            subcommandArgs = Arrays.copyOfRange(args, 1, args.length);
        } else {
            subcommandArgs = new String[0];
        }

        if (args.length < 1) {
            return getExecutor("help").onCommand(sender, command, label, subcommandArgs);
        }

        if (exists(args[0])) {
            return getExecutor(args[0]).onCommand(sender, command, label, subcommandArgs);
        }

        sender.sendMessage(Messages.invalidSubcommand);
        return false;
    }
}
