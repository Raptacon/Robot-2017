package org.usfirst.frc.team3200.robot.CommandGroups;

import org.usfirst.frc.team3200.robot.Robot;
import org.usfirst.frc.team3200.robot.commands.DriveDist;
import org.usfirst.frc.team3200.robot.commands.RotatePID;
import org.usfirst.frc.team3200.robot.commands.SnapToGearPlacement;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPosition extends CommandGroup {

    public LeftPosition() {
requires(Robot.drive);
        
        addSequential(new RotatePID(40));
        addSequential(new DriveDist(10.0));
        addSequential(new RotatePID(-40));
        addSequential(new DriveDist(10.0));
        addSequential(new RotatePID(-30));
        addSequential(new SnapToGearPlacement());
        addSequential(new DriveDist(10.0));
    }
}
