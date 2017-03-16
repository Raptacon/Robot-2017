package org.usfirst.frc.team3200.robot.commands.climber;

import org.usfirst.frc.team3200.robot.Robot;
import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {
    
    Joystick controller;
    public Climb(Joystick controller) {
        super("Climb");
        requires(Robot.climber);
        this.controller = controller;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.climber.set(Math.min(controller.getRawAxis(RobotMap.LEFT_STICK_Y), 0));
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
