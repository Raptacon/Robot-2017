package org.usfirst.frc.team3200.robot.commands.drivetrain;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotatePID extends Command {
	private PIDController pid;

	private double goalRotation;

	public RotatePID(double rotation) {
		super("RotatePID");
		requires(Robot.driveTrain);

		pid = new PIDController(0.02, 0, 0.01, Robot.imu.pidHeading, Robot.driveTrain.pidRotate);
		pid.setInputRange(0, 360);
		pid.setOutputRange(-1, 1);
		pid.setContinuous(true); //IMU wraps from 360 to 0
		pid.setAbsoluteTolerance(2); //degrees

		goalRotation = rotation;

		SmartDashboard.putData("RotatePID", pid);
	}

	protected void initialize() {
		//This line ensures that the result of goal + current is within 0 and 360
		//by adding 360 (to make negative values positive) and modulo 360 (to make values less than 360)
		pid.setSetpoint((goalRotation + Robot.imu.getHeading() + 360) % 360);
		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//execution handled by pid thread
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pid.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		pid.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		pid.disable();
	}

}
