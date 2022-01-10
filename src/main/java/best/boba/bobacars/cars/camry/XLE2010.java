package best.boba.bobacars.cars.camry;

import best.boba.bobacars.car.CarModel;

import java.io.Serializable;

public class XLE2010 implements CarModel {
    static double finalDrive = 3.69;
    static double[] forwardGears = new double[]{3.30, 1.90, 1.42, 1.00, 0.71, 0.61};
    static double reverseGear = 4.15;
    static double revsPerMeter = 1.240804224;

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
