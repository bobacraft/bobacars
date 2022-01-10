package best.boba.bobacars.car;

public class TransmissionDirection {
    private Direction nextDirection = Direction.DRIVE;
    private Direction currentState = Direction.NEUTRAL;

    public enum Direction {
        REVERSE,
        NEUTRAL,
        DRIVE,
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
