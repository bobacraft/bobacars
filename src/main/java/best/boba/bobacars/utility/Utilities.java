package best.boba.bobacars.utility;

import best.boba.bobacars.Config;
import best.boba.bobacars.car.Car;
import best.boba.bobacars.car.CarModel;
import best.boba.bobacars.car.CarModelDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Utilities {
    private Utilities() {}

    public static @Nullable Car getCarFromMinecartData(Config config, RideableMinecart minecart) {
        PersistentDataContainer container = minecart.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(config.getPlugin(), "bobacarModel");
        CarModelDataType dataType = new CarModelDataType();

        if (!container.has(key, dataType)) {
            return null;
        }

        CarModel model = container.get(key, dataType);
        UUID uuid = minecart.getUniqueId();

        if (!config.hasCar(uuid)) {
            Car car = new Car(model);
            config.addCar(uuid, car);
            return car;
        }

        return config.getCar(uuid);
    }

    public static boolean destroyCar(Config config, RideableMinecart minecart) {
        UUID uuid = minecart.getUniqueId();

        Car car = config.getCar(uuid);
        if (car != null) {
            car.destroy();
            config.removeCar(uuid);
        }

        PersistentDataContainer container = minecart.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(config.getPlugin(), "bobacarModel");
        CarModelDataType dataType = new CarModelDataType();

        if (container.has(key, dataType)) {
            container.remove(key);
            return true;
        }

        return false;
    }
}
