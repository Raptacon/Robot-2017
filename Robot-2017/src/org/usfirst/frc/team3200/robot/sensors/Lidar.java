package org.usfirst.frc.team3200.robot.sensors;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Lidar {
    private SerialPort lidar;
    
    public Lidar() {
        lidar = new SerialPort(115200, SerialPort.Port.kUSB1);
        lidar.setReadBufferSize(100);
        lidar.setTimeout(.025);
        lidar.enableTermination();
    }
    
    public double getShooterDistance() {
        lidar.writeString("1\n");
        String output = lidar.readString();
        
        SmartDashboard.putString("Arduino Raw", output);
        
        try {
            return Double.parseDouble(output);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public double getGearDistance() {
        lidar.writeString("2\n");
        String output = lidar.readString();
        
        SmartDashboard.putString("Arduino Raw", output);
        
        try {
            return Double.parseDouble(output);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
