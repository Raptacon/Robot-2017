package org.usfirst.frc.team3200.robot.commands;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Rotate extends Command {
	
	double startHeading;
	double goalHeading;
	double speed;
	
	PIDController pid;
	
    public Rotate(double goalHeading) {
    	super("Rotate");
    	requires(Robot.drive);
        this.goalHeading = goalHeading;
        
        pid = new PIDController(0.1, 0, 0, Robot.gyro, Robot.drive);
        pid.setOutputRange(-1, 1);
        pid.setContinuous(true);
        pid.setAbsoluteTolerance(2);
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.setSetpoint(goalHeading);
    	pid.enable();
    	
    	SmartDashboard.putData("Rotate PID", pid);
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
    	Robot.drive.setMecanum(0,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.setMecanum(0,0,0);
    }
}
