package org.usfirst.frc.team3200.robot.commands.autonomous;

import org.usfirst.frc.team3200.robot.commands.drivetrain.DrivePID;
import org.usfirst.frc.team3200.robot.commands.drivetrain.RotatePID;
import org.usfirst.frc.team3200.robot.commands.drivetrain.SetHeading;
import org.usfirst.frc.team3200.robot.commands.gearplacement.GearPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearLeft extends CommandGroup {

    public GearLeft() {
        // Assuming we place the center of robot 2.5 feet from the left side of
        // the field
        addSequential(new SetHeading(0));
        addSequential(new DrivePID(6.0));
        addSequential(new RotatePID(-30));
        addSequential(new GearPlace(true));
    }
}
