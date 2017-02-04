package org.usfirst.frc.team3200.robot.commands;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDist extends Command {
	public PIDController pid;
    public DriveDist(Double distance) {
    	super("RotatePID");
    	requires(Robot.drive);

        pid = new PIDController(0,0,0,Robot.encoders, Robot.drive);
        pid.setOutputRange(-1, 1);
        pid.setContinuous(true);
        pid.setAbsoluteTolerance(2);
        pid.setSetpoint(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setMecanum(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.setMecanum(0, 0, 0);
    }
}