package org.usfirst.frc.team3200.robot.commands.climber;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

    private double highSpeed;
    private double lowSpeed;

    public Climb() {
        super("Lift");
        requires(Robot.climber);
        highSpeed = 1;
        lowSpeed = 0.7;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.climber.isMaxHeight()) {
            Robot.climber.set(lowSpeed);
        } else {
            Robot.climber.set(highSpeed);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.climber.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.climber.set(0);
    }
}
