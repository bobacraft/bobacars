package best.boba.bobacars.car;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;


public class Car {
    final CarModel model;
    int currentGear;
    TransmissionDirection transmissionDirection;
    double engineRPM;
    double speed;
    final BossBar engineRPMBar;
    final BossBar speedBar;

    public Car(CarModel carModel) {
        this.model = carModel;
        this.currentGear = 1;
        this.transmissionDirection = new TransmissionDirection();
        this.engineRPMBar = Bukkit.createBossBar("0 rpm", BarColor.BLUE, BarStyle.SOLID);
        this.speedBar = Bukkit.createBossBar("0 mph", BarColor.YELLOW, BarStyle.SOLID);
    }

    public double getEngineRPM() {
        return engineRPM;
    }

    public double getSpeed() {
        return speed;
    }

    public BossBar getEngineRPMBar() {
        return engineRPMBar;
    }

    public BossBar getSpeedBar() {
        return speedBar;
    }


    public void addPlayer(Player player) {
        this.engineRPMBar.addPlayer(player);
        this.speedBar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        this.engineRPMBar.removePlayer(player);
        this.speedBar.removePlayer(player);
    }


    public TransmissionDirection.Direction cycleTransmissionDirection() {
        this.transmissionDirection.cycle();
        updateSpeed();
        return this.transmissionDirection.getDirection();
    }

    public void downshift() {
        this.currentGear--;
        updateSpeed();
    }

    public void upshift() {
        this.currentGear++;
        updateSpeed();
    }


    public void setEngineRPM(double engineRPM) {
        this.engineRPM = engineRPM;
        this.engineRPMBar.setTitle((int) this.engineRPM + " rpm");
        this.engineRPMBar.setProgress(this.engineRPM / 7500); // bar is out of 7500 rpm

        updateSpeed();
    }

    public void updateSpeed() {
        double finalDriveRatio = this.model.getFinalDrive();
        double currentGearRatio = this.model.getForwardGear(this.currentGear);
        this.speed = (this.engineRPM / (currentGearRatio * finalDriveRatio))  // convert engine speed to wheel speed
                / (60 * 20)                     // convert to revs per tick
                / this.model.getRevsPerMeter()  // divide by revs per meter
                * this.transmissionDirection.getDirection().getMultiplier(); // apply current tranmission direction

        double kmph = this.speed * 20 * 3600 / 1000;
        this.speedBar.setTitle(String.format("%.2f km/h", kmph));
        this.speedBar.setProgress(kmph / 200);
    }


    public void destroy() {
        this.engineRPMBar.removeAll();
        this.speedBar.removeAll();
    }
}
