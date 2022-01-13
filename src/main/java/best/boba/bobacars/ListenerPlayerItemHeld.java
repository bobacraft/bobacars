package best.boba.bobacars;

import best.boba.bobacars.car.AccelerationType;
import best.boba.bobacars.car.Car;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ListenerPlayerItemHeld implements Listener {
    private final Config config;
    public ListenerPlayerItemHeld(final Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (!(player.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }
        Car car = config.getCar(minecart.getUniqueId());
        if (car == null) {
            return;
        }

        int oldSlot = event.getPreviousSlot();
        int newSlot = event.getNewSlot();

        car.setAcceleration(AccelerationType.ACCELERATE, (double) newSlot / 8);
    }
}
