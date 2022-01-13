package best.boba.bobacars.car;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class CarStatusBars {
    final BossBar engineRPMBar;
    final BossBar speedKPHBar;
    static final double maximumRPM = 7500;
    static final double maximumKPH = 150;

    public CarStatusBars() {
        this.engineRPMBar = Bukkit.createBossBar("0 rpm", BarColor.BLUE, BarStyle.SOLID);
        this.speedKPHBar = Bukkit.createBossBar("0 mph", BarColor.YELLOW, BarStyle.SOLID);
    }

    public void setEngineRPM(double engineRPM) {
        //this.engineRPMBar.setProgress(engineRPM / maximumRPM);
        this.engineRPMBar.setTitle(String.format("%.2f rpm", engineRPM));
    }

    public void setSpeedKPH(double speedKPH) {
        //this.speedKPHBar.setProgress(speedKPH / maximumKPH);
        this.speedKPHBar.setTitle(String.format("%.2f km/h", speedKPH));
    }

    public void addPlayer(Player player) {
        this.engineRPMBar.addPlayer(player);
        this.speedKPHBar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        this.engineRPMBar.removePlayer(player);
        this.speedKPHBar.removePlayer(player);
    }

    public void destroy() {
        this.engineRPMBar.removeAll();
        this.speedKPHBar.removeAll();
    }
}
