package best.boba.bobacars;

import best.boba.bobacars.car.Car;
import best.boba.bobacars.car.GearOutOfBoundsException;
import best.boba.bobacars.utility.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ListenerPlayerInteract implements Listener {
    private final Config config;
    public ListenerPlayerInteract(final Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(player.getVehicle() instanceof RideableMinecart minecart)) {
            return;
        }

        Car car = Utilities.getCarFromMinecartData(config, minecart);
        if (car == null) {
            return;
        }

        try {
            switch (event.getAction()) {
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> car.downshift();
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> car.upshift();
            }
        }
        catch (GearOutOfBoundsException e) {
            player.sendTitle(ChatColor.DARK_RED + "" + car.getCurrentGear(), "", 0, 0, 15);
        }

        player.sendTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + car.getCurrentGear(), "", 0, 0, 15);
        event.setCancelled(true);
    }
}
