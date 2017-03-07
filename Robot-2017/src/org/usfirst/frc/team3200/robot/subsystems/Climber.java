package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

    CANTalon talonLeft;
    CANTalon talonRight;

    public Climber() {
        talonLeft = new CANTalon(RobotMap.TALON_LIFTER1);
        talonRight = new CANTalon(RobotMap.TALON_LIFTER2);
        talonRight.setInverted(true);
    }

    public void set(double speed) {
        talonLeft.set(speed);
        talonRight.set(speed);
    }

    public boolean isMaxHeight() {
        return talonLeft.isFwdLimitSwitchClosed();
    }

    public void initDefaultCommand() {
    }
}
