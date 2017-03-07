package org.usfirst.frc.team3200.robot.commands;

import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleBrake extends InstantCommand {

    public ToggleBrake() {
        super();
       requires(Robot.brakes);
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.brakes.toggle();
        
    }

}
