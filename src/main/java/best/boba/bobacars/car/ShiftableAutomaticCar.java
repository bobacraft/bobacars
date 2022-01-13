package best.boba.bobacars.car;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;


public class ShiftableAutomaticCar implements Car {
    final CarModel model;
    int currentGear = 1;
    TransmissionDirection transmissionDirection = new TransmissionDirection();
    double engineRPM;
    double speed;
    AccelerationType accelerationType = AccelerationType.BRAKE;
    double accelerationWeight = 1;
    CarStatusBars statusBars = new CarStatusBars();

    // TODO: these are temporary
    double maxAcceleration = 5.0 / 20;
    double maxBraking = 6.9 / 20;
    double engineBraking = 5.1 / 20;

    public ShiftableAutomaticCar(CarModel carModel) {
        this.model = carModel;
    }

    @Override
    public int getCurrentGear() {
        return this.currentGear;
    }

    @Override
    public double getEngineRPM() {
        return engineRPM;
    }

    @Override
    public double getSpeedMetersPerTick() {
        return speed;
    }


    @Override
    public TransmissionDirection.Direction cycleTransmissionDirection() {
        this.transmissionDirection.cycle();
        return this.transmissionDirection.getDirection();
    }


    @Override
    public void downshift() throws GearOutOfBoundsException {
        if (this.currentGear < model.getMinimumGear()) {
            throw new GearOutOfBoundsException("Gear would be too low.");
        }
        this.currentGear--;
    }

    @Override
    public void upshift() throws GearOutOfBoundsException {
        if (this.currentGear > model.getMaximumGear()) {
            throw new GearOutOfBoundsException("Gear would be too high.");
        }
        this.currentGear++;
    }


    @Override
    public void setAcceleration(AccelerationType type, double weight) {
        this.accelerationType = type;
        this.accelerationWeight = weight;
    }

    @Override
    public void tickSpeed() {
        double finalDriveRatio = this.model.getFinalDrive();
        double currentGearRatio = this.model.getForwardGear(this.currentGear);

        switch (this.accelerationType) {
            case ACCELERATE -> this.speed += this.maxAcceleration * this.accelerationWeight;
            case BRAKE -> this.speed -= this.maxBraking * this.accelerationWeight;
            case COAST -> this.speed -= this.engineBraking;
        }

        this.engineRPM = this.speed * this.transmissionDirection.getDirection().getMultiplier()
                * this.model.getRevsPerMeter()
                * (60 * 20)
                * (currentGearRatio * finalDriveRatio);

//        this.speed = (this.engineRPM / (currentGearRatio * finalDriveRatio))  // convert engine speed to wheel speed
//                / (60 * 20)                     // convert to revs per tick
//                / this.model.getRevsPerMeter()  // divide by revs per meter
//                * this.transmissionDirection.getDirection().getMultiplier(); // apply current tranmission direction

        this.statusBars.setEngineRPM(this.engineRPM);
        this.statusBars.setSpeedKPH(this.getSpeedMetersPerTick() * 20 * 60 * 60);
    }


    @Override
    public CarStatusBars getStatusBars() {
        return this.statusBars;
    }
}
