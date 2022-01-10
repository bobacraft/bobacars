package best.boba.bobacars.commands;

import best.boba.bobacars.CommandCarsInterface;
import best.boba.bobacars.Config;
import best.boba.bobacars.Messages;
import best.boba.bobacars.car.CarModel;
import best.boba.bobacars.car.CarModelDataType;
import best.boba.bobacars.cars.camry.XLE2010;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.persistence.PersistentDataContainer;
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
        minecart.addPassenger(player);
        minecart.setGravity(false);

        PersistentDataContainer container = minecart.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(config.getPlugin(), "bobacarModel");
        CarModel model = new XLE2010();
        container.set(key, new CarModelDataType(), model);
        sender.sendMessage("Spawned a bobacar.");
        config.getLogger().info("Spawned a bobacar with UUID " + uuid);

        VehicleEnterEvent enterEvent = new VehicleEnterEvent(minecart, player);
        Bukkit.getPluginManager().callEvent(enterEvent);

        return true;
    }
}
