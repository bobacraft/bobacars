package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class Config {
    private final JavaPlugin plugin;
    private final Logger logger;
    private final Map<UUID, Car> cars;

    public Config(@NotNull final JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.cars = new HashMap<>();
    }

    public @NotNull JavaPlugin getPlugin() {
        return plugin;
    }

    public @NotNull Logger getLogger() {
        return logger;
    }

    public @Nullable Car getCar(@NotNull UUID uuid) {
        return cars.get(uuid);
    }

    public @NotNull Map<UUID, Car> getCars() {
        return cars;
    }


    public boolean hasCar(@NotNull UUID uuid) {
        return cars.containsKey(uuid);
    }


    public void addCar(UUID uuid, Car car) {
        cars.put(uuid, car);
    }

    public void removeCar(UUID uuid) {
        cars.remove(uuid);
    }
}
