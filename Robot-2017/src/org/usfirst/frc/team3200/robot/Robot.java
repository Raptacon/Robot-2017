
package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.sensors.GripPipeline;
import org.usfirst.frc.team3200.robot.sensors.Vision;
import org.usfirst.frc.team3200.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain drive;
	
	public static Gyro gyro;
	public static Vision vision;
	
	public static OI oi;
	
	GripPipeline Grip;

	public void robotInit() {
		drive = new DriveTrain();
		
		gyro = new AnalogGyro(0);
		vision = new Vision();
		
		oi = new OI();
		
		Grip = new GripPipeline();
	}

	public void disabledInit() {
		
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		
	}

	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		
	}

	public void teleopPeriodic() {
		synchronized(vision.imgLock) {
			SmartDashboard.putNumber("Rectangle at", vision.centerX);
		}
		
		SmartDashboard.putNumber("Heading", gyro.getAngle());	
		SmartDashboard.putBoolean("Camera Found:", vision.cameraFound());
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
