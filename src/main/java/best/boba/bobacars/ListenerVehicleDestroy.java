package best.boba.bobacars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

public class ListenerVehicleDestroy implements Listener {
    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        Vehicle vehicle = event.getVehicle();
        if (vehicle.getMetadata("isBobacar").get(0).asString().equals("test")) {
            vehicle.setGlowing(!vehicle.isGlowing());
            event.setCancelled(true);
        }
    }
}
