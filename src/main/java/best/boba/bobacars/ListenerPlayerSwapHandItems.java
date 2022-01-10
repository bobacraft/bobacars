package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import best.boba.bobacars.utility.Utilities;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class ListenerPlayerSwapHandItems implements Listener {
    private final Config config;
    public ListenerPlayerSwapHandItems(final Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!(player.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }

        Car car = Utilities.getCarFromMinecartData(config, minecart);
        if (car == null) {
            return;
        }

        String newDirection = car.cycleTransmissionDirection().getAbbreviation();
        player.sendTitle(newDirection, null, 0, 0, 15);
        event.setCancelled(true);
    }
}
