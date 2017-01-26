package org.usfirst.frc.team3200.robot.sensors;

import edu.wpi.first.wpilibj.I2C;

public class IMU {
	private I2C imu;
	
	private double headingAdj;
	
	public IMU() {
		imu = new I2C(I2C.Port.kOnboard, 0x28);
		
		//flip y and z axes
		imu.write(0x42, 0x3);
		
		//set to measurement mode
		imu.write(0x3D, 0x8);
	}
	
	public boolean addressIMU() {
	    return imu.addressOnly();
	}
	
	public void reset() {
	    imu.write(0x3D, 0x1C);
	    imu.write(0x3D, 0x8);
	}
	
	private double readAddress(int address) {
		byte[] buffer = new byte[2];
		imu.read(address, 2, buffer);
		short low = (short)(buffer[0] & 0xFF);
		short high = (short)((buffer[1] << 8) & 0xFF00);
		return (double) (low | high);
	}
	
	public double getHeading() {
		//1 degree per 16 raw
		return readAddress(0x1A) / 16;
	}
	
	public void updateHeadingAdj() {
		double headingPrev = headingAdj;
		headingAdj = getHeading();
		if(headingPrev - headingAdj > 315) {
			headingAdj += 360;
		} else if(headingPrev - headingAdj < -45) {
			headingAdj -= 360;
		}
	}
	
	public double getHeadingAdj() {
		return headingAdj;
	}
	
	public double getRoll() {
		return readAddress(0x1C) / 16;
	}
	
	public double getPitch() {
		return readAddress(0x1E) / 16;
	} 
	
	public double getAccelX() {
		//1 meter per second squared per 100 raw
		return readAddress(0x28) / 100;
	}
	
	public double getAccelY() {
		return readAddress(0x2A) / 100;
	}
}