package best.boba.bobacars.commands;

import best.boba.bobacars.CommandCarsInterface;
import best.boba.bobacars.Config;
import best.boba.bobacars.Messages;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class CommandCarsSpawn implements CommandCarsInterface {
    private Config config = null;
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
        minecart.addPassenger(player);
        minecart.setMetadata("isBobacar", new FixedMetadataValue(config.getPlugin(), "test"));

        return true;
    }
}
