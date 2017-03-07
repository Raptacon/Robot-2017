package org.usfirst.frc.team3200.robot.commands.drivetrain;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetHeading extends InstantCommand {

    private double heading;

    public SetHeading(double heading) {
        this.heading = heading;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.imu.setUniversalHeading(heading);
    }
}
