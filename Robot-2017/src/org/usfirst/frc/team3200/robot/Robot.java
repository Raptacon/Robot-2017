package org.usfirst.frc.team3200.robot;

import org.usfirst.frc.team3200.robot.commands.autonomous.DriveStraight;
import org.usfirst.frc.team3200.robot.commands.autonomous.GearCenter;
import org.usfirst.frc.team3200.robot.commands.autonomous.GearLeft;
import org.usfirst.frc.team3200.robot.commands.autonomous.GearRight;
import org.usfirst.frc.team3200.robot.sensors.GearVision;
import org.usfirst.frc.team3200.robot.sensors.IMU;
import org.usfirst.frc.team3200.robot.sensors.Lidar;
import org.usfirst.frc.team3200.robot.sensors.Lights;
import org.usfirst.frc.team3200.robot.sensors.ShooterVision;
import org.usfirst.frc.team3200.robot.subsystems.Brakes;
import org.usfirst.frc.team3200.robot.subsystems.Climber;
import org.usfirst.frc.team3200.robot.subsystems.Collector;
import org.usfirst.frc.team3200.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3200.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain driveTrain;
    public static Climber climber;
    public static Shooter shooter;
    public static Lights lights;
    public static Brakes brakes;
    public static Collector collector;

    public static IMU imu;
    //public static Lidar lidar;
    public static GearVision gearVision;
    public static ShooterVision shooterVision;

    public static OI oi;
    

    private CommandGroup autoCommand;

    private SendableChooser<CommandGroup> autoChooser;

    public void robotInit() {
        driveTrain = new DriveTrain();
        climber = new Climber();
        shooter = new Shooter();
        brakes = new Brakes();
        collector = new Collector();
        
        
        imu = new IMU();
        gearVision = new GearVision();
        shooterVision = new ShooterVision();
        lights = new Lights();
        //lidar = new Lidar();

        oi = new OI();

        autoChooser = new SendableChooser<CommandGroup>();
        autoChooser.addDefault("Do Nothing", new CommandGroup() {/* empty */});
        autoChooser.addObject("Drive Forward", new DriveStraight());
        autoChooser.addObject("Place Gear Right", new GearRight());
        autoChooser.addObject("Place Gear Center", new GearCenter());
        autoChooser.addObject("Place Gear Left", new GearLeft());

        SmartDashboard.putData("Autonomous Mode", autoChooser);

        SmartDashboard.putData(Scheduler.getInstance());
        brakes.set(false);
        lights.setGearLight(true);
        lights.setShooterLight(true);
    }

    public void disabledInit() {
    }

    public void disabledPeriodic() {
        sendValues();
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        autoCommand = (CommandGroup) autoChooser.getSelected();
        autoCommand.start();
    }

    public void autonomousPeriodic() {
        sendValues();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        driveTrain.resetEncoders();
    }

    public void teleopPeriodic() {
        sendValues();

        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    public void sendValues() {

        SmartDashboard.putBoolean("Camera 1 Found:", gearVision.cameraFound());
        SmartDashboard.putBoolean("Camera 2 Found:", shooterVision.cameraFound());
        SmartDashboard.putNumber("Gear Rectangle 1 at", gearVision.getXposR1());
        SmartDashboard.putNumber("Gear Rectangle 2 at", gearVision.getXposR2());
        SmartDashboard.putNumber("Shooter Rectangle at", shooterVision.getXposR1());

        SmartDashboard.putBoolean("IMU Found:", imu.address() == -96);
        SmartDashboard.putNumber("IMU Heading:", imu.getHeading());
        SmartDashboard.putNumber("Universal Heading:", imu.getUniversalHeading());

        SmartDashboard.putNumber("Average Encoder Distance:", driveTrain.getDistance());

        SmartDashboard.putNumber("Gear Setpoint:", gearVision.getSetPoint());
        SmartDashboard.putNumber("Shooter Setpoint", shooterVision.getSetPoint());
        
        SmartDashboard.putNumber("Shooter Speed", shooter.getShooterSpeed());
        
        SmartDashboard.putBoolean("Brake Enabled", brakes.get());
        
        //SmartDashboard.putNumber("Lidar 1", lidar.getGearDistance());
        //SmartDashboard.putNumber("Lidar 2", lidar.getShooterDistance());
    }
}
