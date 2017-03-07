package org.usfirst.frc.team3200.robot.commands.shooter;

import org.usfirst.frc.team3200.robot.OI;
import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAlign extends Command {
	public int setPoint;
	public PIDController pid;
	
	private double adjustment = 0;

	public ShooterAlign() {
		super("ShootAlign");
		requires(Robot.driveTrain);

		pid = new PIDController(0.02, 0.001, 0.02, Robot.imu.pidHeading, Robot.driveTrain.pidRotate);
		pid.setInputRange(0, 360);
		pid.setOutputRange(-1, 1);
		pid.setContinuous(true);
		pid.setAbsoluteTolerance(2);

		SmartDashboard.putData("Shooter Align PID", pid);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	    Robot.lights.setShooterLight(true);
		//gets setpoint based on measured position of reflective tape
		pid.setSetpoint(Robot.shooterVision.getSetPoint());
		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	    //adjust angle based on D-Pad input (90 = right, 270 = left)
	    if(OI.CONTROLLER_1.getPOV() == 90) {
	        adjustment += 0.1;
	    } else if (OI.CONTROLLER_1.getPOV() == 270) {
	        adjustment -= 0.1;
	    }
	    
		//continuously updates the setpoint as it runs
		pid.setSetpoint(Robot.shooterVision.getSetPoint() + adjustment);
		SmartDashboard.putNumber("Shooter Align Adjustment", adjustment);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.shooterVision.rectanglesDetected();
	}

	// Called once after isFinished returns true
	protected void end() {
	    //Robot.lights.setShooterLight(false);
		pid.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	    //Robot.lights.setShooterLight(false);
		pid.disable();
	}
}
