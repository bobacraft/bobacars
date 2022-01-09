package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class MainLoop extends BukkitRunnable {
    private Config config;
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
        }
    }
}
