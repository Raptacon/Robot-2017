package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.commands.ToggleBrake;
import org.usfirst.frc.team3200.robot.commands.drivetrain.DrivePID;
import org.usfirst.frc.team3200.robot.commands.drivetrain.RotatePID;
import org.usfirst.frc.team3200.robot.commands.drivetrain.SetHeading;
import org.usfirst.frc.team3200.robot.commands.gearplacement.GearPlace;
import org.usfirst.frc.team3200.robot.commands.shooter.Shoot;
import org.usfirst.frc.team3200.robot.commands.shooter.ShooterAlign;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    public static Joystick CONTROLLER_1 = new Joystick(0);
    public static Joystick CONTROLLER_2 = new Joystick(1);

    public Button shootAlignButton;
    public Button gearPlaceButton;
    public Button brakeButton;
    public Button shootButton;
    public Button headingSetButton;
    public Button climbButton;

    public OI() {
        shootAlignButton = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_X);
        gearPlaceButton = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_Y);
        brakeButton = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_B);
        shootButton = new JoystickButton(CONTROLLER_2, RobotMap.RIGHT_BUMPER);
        headingSetButton = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_START);
       

        gearPlaceButton.toggleWhenPressed(new ShooterAlign());
        shootAlignButton.toggleWhenPressed(new GearPlace(false));
        shootButton.whileHeld(new Shoot(.6));
        headingSetButton.whenPressed(new SetHeading(0));
        brakeButton.whenPressed(new ToggleBrake());
        

        SmartDashboard.putData("IMU TEST: Rotate 90 Degrees", new RotatePID(90));
        SmartDashboard.putData("ENCODER TEST: Drive 5 Feet", new DrivePID(5));

        SmartDashboard.putData("Toggle Shooter Light", new InstantCommand() {
            public void execute() {
                Robot.lights.toggleShooterLight();
            }
        });
        
        SmartDashboard.putData("Toggle Gear Light", new InstantCommand() {
            public void execute() {
                Robot.lights.toggleGearLight();
            }
        });
    }

}