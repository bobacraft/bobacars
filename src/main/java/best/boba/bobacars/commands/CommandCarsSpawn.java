package best.boba.bobacars.commands;

import best.boba.bobacars.CommandCarsInterface;
import best.boba.bobacars.Config;
import best.boba.bobacars.Messages;
import best.boba.bobacars.car.Car;
import best.boba.bobacars.cars.camry.XLE2010;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CommandCarsSpawn implements CommandCarsInterface {
    private final Config config;
    public CommandCarsSpawn(final Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        World world = player.getWorld();
        RideableMinecart minecart = (RideableMinecart) world.spawnEntity(player.getLocation(), EntityType.MINECART);
        UUID uuid = minecart.getUniqueId();
        Car car = new Car(new XLE2010(), minecart);
        minecart.addPassenger(player);
        config.addCar(uuid, car);
        sender.sendMessage("Spawned a bobacar.");
        config.getLogger().info("Spawned a bobacar with UUID " + uuid);

        VehicleEnterEvent enterEvent = new VehicleEnterEvent(minecart, player);
        Bukkit.getPluginManager().callEvent(enterEvent);

        return true;
    }
}
