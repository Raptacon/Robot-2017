package org.usfirst.frc.team3200.robot.commands.shooter;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
	double speed;
	long startTime;
	long currentTime;
	
    public Shoot(double speed) {
        super("Shoot");
        requires(Robot.shooter);
        
        this.speed = speed;
        
        SmartDashboard.putNumber("Shooter Speed:", speed);
        SmartDashboard.putNumber("Loader Speed:", speed);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//only runs shooter for first 0.5s, then runs both shooter and loader
    	currentTime = System.currentTimeMillis();
    	if(currentTime - startTime < 500) {
    		Robot.shooter.setShooter(SmartDashboard.getNumber("Shooter Speed:", speed));
    		Robot.shooter.setAgitator(-0.5);
    	} else {
    		Robot.shooter.setShooter(SmartDashboard.getNumber("Shooter Speed:", speed));
    		Robot.shooter.setLoader(SmartDashboard.getNumber("Loader Speed:", speed));
    		Robot.shooter.setAgitator(-0.5);
    	}
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
