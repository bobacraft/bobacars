package best.boba.bobacars;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Config {
    private final Logger logger;
    private final JavaPlugin plugin;

    public Config(final JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public Logger getLogger() {
        return logger;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
