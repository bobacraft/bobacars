package best.boba.bobacars.cars.camry;

import best.boba.bobacars.car.CarModel;

public class XLE2010 implements CarModel {
    private static final double finalDrive = 3.69;
    private static final double[] forwardGears = new double[]{3.30, 1.90, 1.42, 1.00, 0.71, 0.61};
    private static final double reverseGear = 4.15;
    private static final double revsPerMeter = 1.240804224;

    @Override
    public double getFinalDrive() {
        return finalDrive;
    }

    @Override
    public double[] getForwardGears() {
        return forwardGears;
    }

    @Override
    public double getForwardGear(int gear) {
        return forwardGears[gear - 1];
    }

    @Override
    public double getReverseGear() {
        return reverseGear;
    }

    @Override
    public double getRevsPerMeter() {
        return revsPerMeter;
    }
}
