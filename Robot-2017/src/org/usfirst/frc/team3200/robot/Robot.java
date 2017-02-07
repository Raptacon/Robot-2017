
package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.sensors.Encoders;
import org.usfirst.frc.team3200.robot.sensors.Gyro;
import org.usfirst.frc.team3200.robot.sensors.IMU;
import org.usfirst.frc.team3200.robot.sensors.Vision;
import org.usfirst.frc.team3200.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

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
	public static CANTalon PIDTalon;
	public static Gyro gyro;
	public static IMU imu;
	public static Vision vision;
	public static Encoders encoders;
	
	public static OI oi;

	public void robotInit() {
		drive = new DriveTrain();
		
		gyro = new Gyro();
		imu = new IMU();
		vision = new Vision();
		
		oi = new OI();
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
		
		SmartDashboard.putNumber("Rectangle 1 at", vision.getXposR1());
		SmartDashboard.putNumber("Rectangle 2 at", vision.getXposR2());
		
		SmartDashboard.putNumber("Gyro Heading", gyro.getHeading());
		SmartDashboard.putBoolean("Camera Found:", vision.cameraFound());
		SmartDashboard.putNumber("IMU Address:", imu.address());
		SmartDashboard.putNumber("IMU Heading:", imu.getHeading());
		SmartDashboard.putNumber("IMU Heading Adjusted:", imu.getHeadingAdj());
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
