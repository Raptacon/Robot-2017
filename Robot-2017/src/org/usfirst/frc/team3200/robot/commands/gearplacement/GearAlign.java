package org.usfirst.frc.team3200.robot.commands.gearplacement;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearAlign extends Command {
	public int setPoint;
	public PIDController pid;

	public GearAlign() {
		super("GearAlign");
		requires(Robot.driveTrain);

		pid = new PIDController(0.02, 0, 0.02, Robot.imu.pidHeading, Robot.driveTrain.pidRotate);
		pid.setInputRange(0, 360);
		pid.setOutputRange(-1, 1);
		pid.setContinuous(true);
		pid.setAbsoluteTolerance(2);

		SmartDashboard.putData("Gear Align PID", pid);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//gets setpoint based on measured position of reflective tape
		pid.setSetpoint(Robot.gearVision.getSetPoint());
		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//continuously updates the setpoint as it runs
		pid.setSetpoint(Robot.gearVision.getSetPoint());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.gearVision.rectanglesDetected();
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
