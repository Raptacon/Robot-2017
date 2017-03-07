package org.usfirst.frc.team3200.robot.commands.autonomous;

import org.usfirst.frc.team3200.robot.commands.drivetrain.SetHeading;
import org.usfirst.frc.team3200.robot.commands.gearplacement.GearPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearCenter extends CommandGroup {

    public GearCenter() {
        addSequential(new SetHeading(-90));
        addSequential(new GearPlace(true));
    }
}
