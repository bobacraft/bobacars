package best.boba.bobacars.car;

public class CarModel {
    static double finalDrive;
    static double[] forwardGears;
    static double[] reverseGears;
    static double revsPerMeter;

    public double getFinalDrive() {
        return finalDrive;
    }

    public double[] getForwardGears() {
        return forwardGears;
    }

    public double[] getReverseGears() {
        return reverseGears;
    }

    // TODO: don't let these next 2 functions use out of bounds index
    public double getForwardGear(int gear) {
        return forwardGears[gear - 1];
    }

    public double getReverseGear(int gear) {
        return reverseGears[gear - 1];
    }

    public double getRevsPerMeter() {
        return revsPerMeter;
    }
}
