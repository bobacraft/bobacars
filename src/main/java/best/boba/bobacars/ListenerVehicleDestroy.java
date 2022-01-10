package best.boba.bobacars;

import best.boba.bobacars.messages.BobacarMessages;
import best.boba.bobacars.utility.Utilities;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

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

        if (!Utilities.destroyCar(config, minecart)) {
            return;
        }

        if (event.getAttacker() instanceof Player player) {
            player.sendMessage(BobacarMessages.destroyed);
        }
    }
}
