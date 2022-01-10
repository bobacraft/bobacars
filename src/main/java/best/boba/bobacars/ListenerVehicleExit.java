package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import best.boba.bobacars.messages.BobacarMessages;
import best.boba.bobacars.utility.Utilities;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class ListenerVehicleExit implements Listener {
    private final Config config;
    public ListenerVehicleExit(final Config config) {
        this.config = config;
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }
        if (!(event.getExited() instanceof Player player)) {
            return;
        }

        Car car = Utilities.getCarFromMinecartData(config, minecart);
        if (car == null) {
            return;
        }

        car.removePlayer(player);
        player.sendMessage(BobacarMessages.exited);
    }
}
