package best.boba.bobacars.car;

import org.bukkit.ChatColor;

public class TransmissionDirection {
    private Direction nextDirection = Direction.DRIVE;
    private Direction currentState = Direction.NEUTRAL;

    public enum Direction {
        REVERSE (ChatColor.RED + "R", -1),
        NEUTRAL (ChatColor.GREEN + "N", 0),
        DRIVE (ChatColor.DARK_GREEN + "D", 1),
        ;

        private final String abbreviation;
        private final int multiplier;
        Direction(String abbreviation, int multiplier) {
            this.abbreviation = abbreviation;
            this.multiplier = multiplier;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public int getMultiplier() {
            return multiplier;
        }
    }

    public Direction getDirection() {
        return currentState;
    }

    public void cycle() {
        if (currentState != Direction.NEUTRAL) {
            currentState = Direction.NEUTRAL;
            return;
        }

        currentState = nextDirection;
        switch (nextDirection) {
            case DRIVE -> nextDirection = Direction.REVERSE;
            case REVERSE -> nextDirection = Direction.DRIVE;
        }
    }
}
