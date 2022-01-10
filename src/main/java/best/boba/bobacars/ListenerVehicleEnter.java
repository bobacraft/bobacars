package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import best.boba.bobacars.car.CarModel;
import best.boba.bobacars.car.CarModelDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class ListenerVehicleEnter implements Listener {
    private final Config config;
    public ListenerVehicleEnter(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }
        if (!(event.getEntered() instanceof Player player)) {
            return;
        }

        PersistentDataContainer container = minecart.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(config.getPlugin(), "bobacarModel");
        CarModelDataType dataType = new CarModelDataType();

        if (!container.has(key, dataType)) {
            return;
        }

        CarModel model = container.get(key, dataType);
        UUID uuid = minecart.getUniqueId();
        Car car;
        if (!config.hasCar(uuid)) {
            car = new Car(model);
            config.addCar(uuid, car);
        } else {
            car = config.getCar(uuid);
        }

        car.getEngineRPMBar().addPlayer(player);
        car.getSpeedBar().addPlayer(player);
        player.sendMessage("Entered a bobacar.");
    }
}
