package org.usfirst.frc.team3200.robot.commands;

import org.usfirst.frc.team3200.robot.Robot;
import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MecanumDriveControlled extends Command {
	private Joystick controller;
	
	public MecanumDriveControlled(Joystick controller) {
		super("MecanumDriveControlled");
		this.controller = controller;
		requires(Robot.drive);
	}

	
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double speedY = controller.getRawAxis(RobotMap.LEFT_STICK_Y) * 0.6;
		double speedX = controller.getRawAxis(RobotMap.LEFT_STICK_X) * 0.6;
		double rot    = controller.getRawAxis(RobotMap.RIGHT_STICK_X) * 0.6;
		
		double turbo = controller.getRawAxis(RobotMap.RIGHT_TRIGGER);
		
		speedX = speedX * (1 + turbo * 0.66);
		speedY = speedY * (1 + turbo * 0.66);
		
		if(Math.abs(speedX) <= .1){
			speedX = 0;
		}
		
		if(Math.abs(speedY) <= .1){
			speedY = 0;
		}
		
		if(Math.abs(rot) <= .1){
			rot = 0;
		}
		Robot.drive.setMecanum(speedX, speedY, rot);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end(){
		Robot.drive.setMecanum(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.setMecanum(0, 0, 0);
	}
}
