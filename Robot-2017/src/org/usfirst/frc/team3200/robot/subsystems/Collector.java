package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Collector extends Subsystem {
    public CANTalon collectorLeft;
    public CANTalon collectorRight;
    
    public Collector(){
        collectorLeft = new CANTalon(RobotMap.TALON_COLLECTOR_LEFT);
        collectorRight = new CANTalon(RobotMap.TALON_COLLECTOR_RIGHT);
    }
    
    public void set(double speed){
        collectorLeft.set(speed);
        collectorRight.set(speed);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

