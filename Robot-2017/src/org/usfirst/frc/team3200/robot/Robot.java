
package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.sensors.IMU;
import org.usfirst.frc.team3200.robot.sensors.GripPipeline;
import org.usfirst.frc.team3200.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	
	public static IMU imu;
	
	public static OI oi;
	
	GripPipeline Grip;

	public void robotInit() {
		drive = new DriveTrain();
		
		imu = new IMU();
		
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
		imu.updateHeadingAdj();
		
		Scheduler.getInstance().run();
	}

	public void teleopInit() {

	}

	public void teleopPeriodic() {
		imu.updateHeadingAdj();
		
		SmartDashboard.putBoolean("IMU found:", !imu.addressIMU());
		SmartDashboard.putNumber("Heading", imu.getHeading());
		SmartDashboard.putNumber("Heading Adjusted", imu.getHeadingAdj());
		
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
