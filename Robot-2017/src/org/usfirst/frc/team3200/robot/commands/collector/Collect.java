package org.usfirst.frc.team3200.robot.commands.collector;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Collect extends Command {
    double speed;
    
    public Collect(double speed) {
        requires(Robot.collector);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.collector.set(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.collector.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.collector.set(0);
    }
}
