package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import best.boba.bobacars.car.CarModel;
import best.boba.bobacars.car.CarModelDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class ListenerVehicleDestroy implements Listener {
    private final Config config;
    public ListenerVehicleDestroy(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }

        PersistentDataContainer container = minecart.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(config.getPlugin(), "bobacarModel");
        CarModelDataType dataType = new CarModelDataType();

        if (!container.has(key, dataType)) {
            return;
        }

        container.remove(key);

        CarModel model = container.get(key, dataType);
        UUID uuid = minecart.getUniqueId();
        Car car = config.getCar(uuid);

        if (car != null) {
            car.destroy();
            config.removeCar(uuid);
        }

        config.getLogger().info("Destroyed a bobacar with UUID " + uuid);
        if (event.getAttacker() instanceof Player player) {
            player.sendMessage("Destroyed a bobacar.");
        }
    }
}
