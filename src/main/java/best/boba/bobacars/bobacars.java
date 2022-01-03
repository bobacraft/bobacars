package best.boba.bobacars;

import org.bukkit.plugin.java.JavaPlugin;

public class bobacars extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("Unloaded");
    }
}
