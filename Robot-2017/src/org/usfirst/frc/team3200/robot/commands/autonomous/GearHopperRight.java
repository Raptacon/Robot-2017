package org.usfirst.frc.team3200.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearHopperRight extends CommandGroup {

    public GearHopperRight() {
        addSequential(new GearRight());
        addSequential(new WaitCommand(1));
        //TODO add hopper pushing
    }
}
