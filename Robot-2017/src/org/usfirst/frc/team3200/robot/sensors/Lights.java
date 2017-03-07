package org.usfirst.frc.team3200.robot.sensors;

import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;


public class Lights {
    private Solenoid gearLight;
    private Solenoid shooterLight;
    public Lights(){
        gearLight = new Solenoid(RobotMap.GEAR_LIGHT);
        shooterLight = new Solenoid(RobotMap.SHOOTER_LIGHT);
    }
    
    public void setGearLight(boolean value){
        gearLight.set(value);
    }
    
    public void toggleGearLight() {
        gearLight.set(!gearLight.get());
    }
    
    public void setShooterLight(boolean value){
        shooterLight.set(value);
    }
    
    public void toggleShooterLight() {
        shooterLight.set(!shooterLight.get());
    }
}
