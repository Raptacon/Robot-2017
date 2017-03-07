package org.usfirst.frc.team3200.robot.commands.autonomous;

import org.usfirst.frc.team3200.robot.commands.drivetrain.DrivePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraight extends CommandGroup {

    public DriveStraight() {
        addSequential(new DrivePID(4));
    }
}
