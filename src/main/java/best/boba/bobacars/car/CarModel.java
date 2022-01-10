package best.boba.bobacars.car;

import java.io.Serializable;

public interface CarModel extends Serializable {
    double getFinalDrive();
    double[] getForwardGears();
    double getForwardGear(int gear);
    double getReverseGear();
    double getRevsPerMeter();
}
