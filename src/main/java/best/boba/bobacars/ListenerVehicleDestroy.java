package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

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
        UUID uuid = minecart.getUniqueId();
        Car car = config.getCar(uuid);
        if (car == null) {
            return;
        }

        car.destroy();
        config.removeCar(uuid);
        config.getLogger().info("Destroyed a bobacar with UUID " + uuid);

        if ((event.getAttacker() instanceof Player player)) {
            player.sendMessage("Destroyed a bobacar.");
        }
    }
}
