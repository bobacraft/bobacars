package best.boba.bobacars.car;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.util.Vector;


public class Car {
    final CarModel model;
    int currentGear;
    double engineRPM;
    final BossBar engineRPMBar;
    final BossBar speedBar;

    public Car(CarModel carModel, RideableMinecart minecart) {
        this.model = carModel;
        this.currentGear = 1;
        this.engineRPMBar = Bukkit.createBossBar("0 rpm", BarColor.BLUE, BarStyle.SOLID);
        this.speedBar = Bukkit.createBossBar("0 mph", BarColor.YELLOW, BarStyle.SOLID);
    }

    public double getEngineRPM() {
        return engineRPM;
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


    public void setEngineRPM(double engineRPM, RideableMinecart minecart) {
        this.engineRPM = engineRPM;

        double finalDriveRatio = this.model.getFinalDrive();
        double currentGearRatio = this.model.getForwardGear(this.currentGear);
        double speed = (this.engineRPM / (currentGearRatio * finalDriveRatio))  // convert engine speed to wheel speed
                            / (60 * 20)                     // convert to revs per tick
                            / this.model.getRevsPerMeter(); // divide by revs per meter - TODO: replace this with variable
        Vector vector = new Vector(speed, 0, 0);
        minecart.setVelocity(vector);
    }


    public void updateEngineRPM() {
        this.engineRPMBar.setTitle((int) this.engineRPM + " rpm");
        this.engineRPMBar.setProgress(this.engineRPM / 7500); // bar is out of 7500 rpm
    }


    public void destroy() {
        this.engineRPMBar.removeAll();
        this.speedBar.removeAll();
    }
}
