package org.usfirst.frc.team3200.robot.commands.gearplacement;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearPlace extends Command {

    private PIDController pid;
    private boolean autoStop;

    public GearPlace(boolean autoStop) {
        super("GearPlace");
        requires(Robot.driveTrain);

        pid = new PIDController(0.02, 0, 0, Robot.imu.pidHeading, Robot.driveTrain.pidRotateStrafe);
        pid.setInputRange(0, 360);
        pid.setOutputRange(-1, 1);
        pid.setContinuous(true);
        pid.setAbsoluteTolerance(2);
        SmartDashboard.putData("Gear Place PID", pid);
        this.autoStop = autoStop;
        
        setTimeout(3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // sets setpoint to current heading
        pid.setSetpoint(Robot.gearVision.getSetPoint());
        pid.enable();
        Robot.lights.setGearLight(true);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        pid.setSetpoint(Robot.gearVision.getSetPoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(autoStop) {
            return isTimedOut();
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.disable();
        //Robot.lights.setGearLight(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        pid.disable();
        //Robot.lights.setGearLight(false);
    }
}

