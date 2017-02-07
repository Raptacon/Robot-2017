package org.usfirst.frc.team3200.robot.sensors;

import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Gyro implements PIDSource {
	private AnalogGyro gyro;
	
	public Gyro() {
		gyro = new AnalogGyro(RobotMap.GYRO);
		gyro.setSensitivity(0.007);
	}
	
	public double getHeading() {
		return gyro.getAngle();
	}
	
	public void reset() {
		gyro.reset();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getHeading();
	}
}
