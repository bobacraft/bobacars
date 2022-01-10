package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;

public class MainLoop extends BukkitRunnable {
    private final Config config;
    public MainLoop(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        for (Map.Entry<UUID, Car> entry : config.getCars().entrySet()) {
            if (!(Bukkit.getEntity(entry.getKey()) instanceof RideableMinecart minecart)) {
                continue;
            }
            try {
                if (!(minecart.getPassengers().get(0) instanceof Player player)) {
                    continue;
                }
            }
            catch (IndexOutOfBoundsException e) {
                continue;
            }

            Car car = entry.getValue();
            Vector velocity = new Vector(car.getSpeed(), 0, 0);
            minecart.setVelocity(velocity);
        }
    }
}
