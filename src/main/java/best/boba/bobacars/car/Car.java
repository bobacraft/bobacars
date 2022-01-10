package best.boba.bobacars.car;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;


public class Car {
    final CarModel model;
    int currentGear;
    double engineRPM;
    double speed;
    final BossBar engineRPMBar;
    final BossBar speedBar;

    public Car(CarModel carModel) {
        this.model = carModel;
        this.currentGear = 1;
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


    public void downshift() {
        this.currentGear--;
    }

    public void upshift() {
        this.currentGear++;
    }


    public void setEngineRPM(double engineRPM) {
        this.engineRPM = engineRPM;
        this.engineRPMBar.setTitle((int) this.engineRPM + " rpm");
        this.engineRPMBar.setProgress(this.engineRPM / 7500); // bar is out of 7500 rpm

        double finalDriveRatio = this.model.getFinalDrive();
        double currentGearRatio = this.model.getForwardGear(this.currentGear);
        this.speed = (this.engineRPM / (currentGearRatio * finalDriveRatio))  // convert engine speed to wheel speed
                / (60 * 20)                     // convert to revs per tick
                / this.model.getRevsPerMeter(); // divide by revs per meter

        double kmph = this.speed * 20 * 3600 / 1000;
        this.speedBar.setTitle(String.format("%.2f km/h", kmph));
        this.speedBar.setProgress(kmph / 200);
    }


    public void destroy() {
        this.engineRPMBar.removeAll();
        this.speedBar.removeAll();
    }
}
