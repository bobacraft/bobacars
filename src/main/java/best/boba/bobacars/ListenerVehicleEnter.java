package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import java.util.UUID;

public class ListenerVehicleEnter implements Listener {
    private Config config;
    public ListenerVehicleEnter(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }
        UUID uuid = minecart.getUniqueId();
        Car car = config.getCar(uuid);
        if (car == null) {
            return;
        }
        if (!(event.getEntered() instanceof Player player)) {
            return;
        }

        car.getEngineRPMBar().addPlayer(player);
        car.getSpeedBar().addPlayer(player);
        player.sendMessage("Entered a bobacar.");
    }
}
