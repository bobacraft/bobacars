package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.UUID;

public class ListenerVehicleExit implements Listener {
    private final Config config;
    public ListenerVehicleExit(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }
        UUID uuid = minecart.getUniqueId();
        Car car = config.getCar(uuid);
        if (car == null) {
            return;
        }
        if (!(event.getExited() instanceof Player player)) {
            return;
        }

        car.getEngineRPMBar().removePlayer(player);
        car.getSpeedBar().removePlayer(player);
        player.sendMessage("Exited a bobacar.");
    }
}
