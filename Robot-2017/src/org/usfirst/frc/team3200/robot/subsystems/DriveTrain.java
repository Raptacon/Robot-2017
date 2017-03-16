package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.OI;
import org.usfirst.frc.team3200.robot.RobotMap;
import org.usfirst.frc.team3200.robot.commands.drivetrain.MecanumDriveControlled;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private RobotDrive drive;

	CANTalon talonFL;
	CANTalon talonBL;
	CANTalon talonFR;
	CANTalon talonBR;

	public DriveTrain() {
		super("MecanumDrive");

		talonFR = new CANTalon(RobotMap.TALON_FR);
		talonBR = new CANTalon(RobotMap.TALON_BR);
		talonFL = new CANTalon(RobotMap.TALON_FL);
		talonBL = new CANTalon(RobotMap.TALON_BL);

		talonFR.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonBR.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonFL.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonBL.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		talonBL.setInverted(true);
		talonFL.setInverted(true);
		talonBL.reverseSensor(true);
		talonFL.reverseSensor(true);

		drive = new RobotDrive(talonFR, talonBR, talonFL, talonBL);

		drive.setSafetyEnabled(false);
	}

	public void setMecanum(double x, double y, double rot) {
		drive.mecanumDrive_Cartesian(x, y, rot, 0);
	}
	
	public void setMecanum(double x, double y, double rot, double angle) {
	    drive.mecanumDrive_Cartesian(x, y, rot, angle);
	}

	public double getDistance() {
		SmartDashboard.putNumber("Front Right Encoder Count:", talonFR.getPosition());
		SmartDashboard.putNumber("Back Right Encoder Count:", talonBR.getPosition());
		SmartDashboard.putNumber("Front Left Encoder Count:", talonFL.getPosition());
		SmartDashboard.putNumber("Back Left Encoder Count:", talonBL.getPosition());
	    
		return (talonFR.getPosition() + 
				talonBR.getPosition() + 
				//talonFL.getPosition()
				talonBL.getPosition()
				) / 3 / 900;
	}
	
	public void resetEncoders() {
		talonFR.setPosition(0);
		talonBR.setPosition(0);
		talonFL.setPosition(0);
		talonBL.setPosition(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MecanumDriveControlled(OI.CONTROLLER_1));
	}

	public PIDOutput pidRotate = new PIDOutput() {
		public void pidWrite(double output) {
			setMecanum(0, 0, output);
		}
	};

	public PIDOutput pidDrive = new PIDOutput() {
		public void pidWrite(double output) {
			setMecanum(0, output, 0);
		}
	};
	
	public PIDOutput pidStrafe = new PIDOutput() {
		public void pidWrite(double output) {
			setMecanum(output, 0.2, 0);
		}
	};
	
	public PIDOutput pidRotateStrafe = new PIDOutput() {
	    public void pidWrite(double output) {
	        setMecanum(0.4, 0, output);
	    }
	};

	public PIDSource pidDistance = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return getDistance();
		}
	};

}
