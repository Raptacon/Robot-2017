package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.OI;
import org.usfirst.frc.team3200.robot.RobotMap;
import org.usfirst.frc.team3200.robot.commands.MecanumDriveControlled;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem implements PIDOutput {
	private RobotDrive drive;
	
	CANTalon talonFL;
	CANTalon talonBL;
	CANTalon talonFR;
	CANTalon talonBR;

	
	public DriveTrain(){
		super("MecanumDrive");
		
		talonFR = new CANTalon(RobotMap.TALON_FR);
		talonBR = new CANTalon(RobotMap.TALON_BR);
		talonFL = new CANTalon(RobotMap.TALON_FL);
		talonBL = new CANTalon(RobotMap.TALON_BL);
		
		drive = new RobotDrive(talonFR, talonBR, talonFL, talonBL);
		
		drive.setInvertedMotor(MotorType.kRearRight, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		
	    drive.setSafetyEnabled(false);
	    
	}

	public void setMecanum(double x, double y, double rot){
		drive.mecanumDrive_Cartesian(x, y, rot, 0);
	}
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveControlled(OI.CONTROLLER_1));
    }
    
     

	@Override
	public void pidWrite(double output) {
        setMecanum(0, 0, output);
    }
}

