package org.usfirst.frc.team3200.robot.commands.shooter;

import org.usfirst.frc.team3200.robot.Robot;
import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootControlled extends Command {
    Joystick controller;
    public ShootControlled(Joystick controller) {
        super("ShootControlled");
        requires(Robot.shooter);
        this.controller = controller;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speedRight = controller.getRawAxis(RobotMap.RIGHT_TRIGGER);
        double speedLeft = -controller.getRawAxis(RobotMap.LEFT_TRIGGER);
       
        Robot.shooter.setShooter(speedRight + speedLeft);
        Robot.shooter.setLoader(speedRight + speedLeft);
        Robot.shooter.setAgitator(speedRight + speedLeft);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.setShooter(0);
        Robot.shooter.setLoader(0);
        Robot.shooter.setAgitator(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.shooter.setShooter(0);
        Robot.shooter.setLoader(0);
        Robot.shooter.setAgitator(0);
    }
}
