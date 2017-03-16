package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.OI;
import org.usfirst.frc.team3200.robot.Robot;
import org.usfirst.frc.team3200.robot.RobotMap;
import org.usfirst.frc.team3200.robot.commands.shooter.ShootControlled;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	CANTalon loader;
	CANTalon shooter;
	CANTalon agitator;

	public Shooter() {
		loader = new CANTalon(RobotMap.TALON_LOADER);
		shooter = new CANTalon(RobotMap.TALON_SHOOTER);
		agitator = new CANTalon(RobotMap.TALON_MIXER);
		
		shooter.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		shooter.setPIDSourceType(PIDSourceType.kRate);
		shooter.setPID(0.1, 0, 0);
		
	}

	public void setLoader(double speed) {

		loader.set(speed);
	}
	
	public void setShooter(double speed) {
		shooter.setSetpoint(speed);
		shooter.enable();
	    //shooter.set(speed);
		
	}
	
	public void setAgitator(double speed) {
	    agitator.set(speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShootControlled(OI.CONTROLLER_2));
	}
	
	public double getShooterSpeed(){
	    return shooter.getSpeed();
	}
	
}
