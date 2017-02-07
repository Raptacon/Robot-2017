package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.commands.RotatePID;
import org.usfirst.frc.team3200.robot.commands.SnapToGearPlacement;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Joystick CONTROLLER_1 = new Joystick(0);
	public Button x;
	public Button y;
	public OI(){
		x = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_X);
		y = new JoystickButton(CONTROLLER_1, RobotMap.BUTTON_Y);
		x.whenPressed(new RotatePID(100));
		y.whenPressed(new SnapToGearPlacement());
	}
	

}

