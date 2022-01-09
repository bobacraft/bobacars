package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class Config {
    private final JavaPlugin plugin;
    private final Logger logger;
    private final Map<UUID, Car> cars;

    public Config(final JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.cars = new HashMap<>();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public Car getCar(UUID uuid) {
        return cars.get(uuid);
    }

    public Map<UUID, Car> getCars() {
        return cars;
    }


    public void addCar(UUID uuid, Car car) {
        cars.put(uuid, car);
    }

    public void removeCar(UUID uuid) {
        cars.remove(uuid);
    }
}
