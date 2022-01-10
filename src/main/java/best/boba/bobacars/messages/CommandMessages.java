package best.boba.bobacars.messages;

import org.bukkit.ChatColor;

public class CommandMessages {
    private CommandMessages() {}

    public static final String help = ChatColor.RED + "Good luck.";
    public static final String senderIsNotPlayer = ChatColor.RED + "This command can only be used by a player.";
    public static final String notEnoughArguments = ChatColor.RED + "Not enough arguments.";
    public static final String invalidSubcommand = ChatColor.RED + "Invalid subcommand.";

    public static final String notRidingBobacar = ChatColor.RED + "You are not currently in a bobacar.";
}
