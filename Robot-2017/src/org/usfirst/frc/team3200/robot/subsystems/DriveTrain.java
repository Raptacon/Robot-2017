package org.usfirst.frc.team3200.robot.subsystems;
import com.ctre.CANTalon;
import org.usfirst.frc.team3200.robot.RobotMap;
import org.usfirst.frc.team3200.robot.commands.MecanumDriveControlled;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private RobotDrive drive;
	
	public DriveTrain(){
		super("MecanumDrive");
		
		drive = new RobotDrive(
				new CANTalon(RobotMap.TALON_FL),
				new CANTalon(RobotMap.TALON_BL),
				new CANTalon(RobotMap.TALON_FR),
				new CANTalon(RobotMap.TALON_BR)
				);
		
		drive.setInvertedMotor(MotorType.kRearRight, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		
	    drive.setSafetyEnabled(false);
	}

	public void setMecanum(double x, double y, double rot){
		drive.mecanumDrive_Cartesian(x, y, rot, 0);
	}
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveControlled(RobotMap.CONTROLLER_1));
    }
    
    
}

