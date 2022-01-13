package best.boba.bobacars.car;

public interface Car {
    int getCurrentGear();
    double getEngineRPM();
    double getSpeedMetersPerTick();
    TransmissionDirection.Direction cycleTransmissionDirection();
    void downshift() throws GearOutOfBoundsException;
    void upshift() throws GearOutOfBoundsException;
    void setAcceleration(AccelerationType type, double weight);
    void tickSpeed();

    CarStatusBars getStatusBars();
}
