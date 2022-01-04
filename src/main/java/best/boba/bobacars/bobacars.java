package best.boba.bobacars;

import best.boba.bobacars.commands.CommandCarsHelp;
import best.boba.bobacars.commands.CommandCarsSpawn;
import best.boba.bobacars.completers.CompleterCarsHelp;
import best.boba.bobacars.completers.CompleterCarsSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public class bobacars extends JavaPlugin {
    private Config config = null;

    // https://bukkit.org/threads/tutorial-sub-commands-in-different-classes.203341/
    public void registerCarCommands() {
        CommandCarsHandler handler = new CommandCarsHandler(config);
        CompleterCarsHandler completer = new CompleterCarsHandler(config);

        handler.register("help", new CommandCarsHelp(config));
        handler.register("spawn", new CommandCarsSpawn(config));

        completer.register("help", new CompleterCarsHelp(config));
        completer.register("spawn", new CompleterCarsSpawn(config));

        getCommand("cars").setExecutor(handler);
        getCommand("cars").setTabCompleter(completer);
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new ListenerVehicleDestroy(), this);
    }

    @Override
    public void onEnable() {
        this.config = new Config(this);
        registerCarCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
    }
}
