package org.usfirst.frc.team3200.robot.commands.drivetrain;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivePID extends Command {

    PIDController pid;

    double goalDistance;
    double startDistance;

    public DrivePID(double distance) {
        super("DrivePID");
        requires(Robot.driveTrain);

        goalDistance = distance;

        pid = new PIDController(0.25, 0, 0, Robot.driveTrain.pidDistance,
                Robot.driveTrain.pidDrive);
        // Input range is infinite
        pid.setOutputRange(-1, 1);
        pid.setAbsoluteTolerance(0.2); // feet

        SmartDashboard.putData("Drive PID:", pid);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pid.setSetpoint(goalDistance + Robot.driveTrain.getDistance());
        pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // execution handled by pid thread
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
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
