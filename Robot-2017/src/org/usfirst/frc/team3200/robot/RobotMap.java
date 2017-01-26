package org.usfirst.frc.team3200.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
	//Controllers
    public static final Joystick CONTROLLER_1 = new Joystick(0);
    
    //Controller Axes
  	public static final int LEFT_STICK_X  = 0;
  	public static final int LEFT_STICK_Y  = 1;
  	public static final int LEFT_TRIGGER  = 2;
  	public static final int RIGHT_TRIGGER = 3;
  	public static final int RIGHT_STICK_X = 4;
  	public static final int RIGHT_STICK_Y = 5;
  	
  	//Controller Buttons
  	public static final int BUTTON_A              = 1;
  	public static final int BUTTON_B              = 2;
  	public static final int BUTTON_X              = 3;
  	public static final int BUTTON_Y              = 4;
  	public static final int LEFT_BUMPER           = 5;
  	public static final int RIGHT_BUMPER          = 6;
  	public static final int BUTTON_SELECT         = 7;
  	public static final int BUTTON_START          = 8;
  	public static final int BUTTON_LEFT_JOYSTICK  = 9;
  	public static final int BUTTON_RIGHT_JOYSTICK = 10;
  	
  	public static final int TALON_FR = 9;
  	public static final int TALON_BR = 12;
  	public static final int TALON_FL = 10;
  	public static final int TALON_BL = 11;
  	

}
