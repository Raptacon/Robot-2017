package org.usfirst.frc.team3200.robot.sensors;

import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Encoders implements PIDSource {
	
	public Encoder encoderFL;
	public Encoder encoderFR;
	public Encoder encoderBL;
	public Encoder encoderBR;
	
	private PIDSourceType sourceType = PIDSourceType.kDisplacement;
	
	//when driving on carpet at 1.0 speed
	private double distPerPulse = 0.0042149631 * 1.1583333333 * 0.7416666667 * 2; //feet
	
	public Encoders(){
		encoderFL = new Encoder(RobotMap.ENCODER_FLA, RobotMap.ENCODER_FLB);
		encoderFR = new Encoder(RobotMap.ENCODER_FRA, RobotMap.ENCODER_FRB);
		encoderBL = new Encoder(RobotMap.ENCODER_BLA, RobotMap.ENCODER_BLB);
		encoderBR = new Encoder(RobotMap.ENCODER_BRA, RobotMap.ENCODER_BRB);
		
		encoderFL.setDistancePerPulse(distPerPulse);
		encoderFR.setDistancePerPulse(distPerPulse);
		encoderBL.setDistancePerPulse(distPerPulse);
		encoderBR.setDistancePerPulse(distPerPulse);
		
		encoderFL.reset();
		encoderFR.reset();
		encoderBL.reset();
		encoderBR.reset();
	}
	
	public double getAverageDistance(){
		return ((encoderFL.getDistance() + encoderFR.getDistance() + encoderBL.getDistance() + encoderBR.getDistance()) / 4);
	}
	
	public double getLeft() {
		return (encoderFL.getDistance() + encoderBL.getDistance())/2;
	}
	
	public double getRight() {
		return (encoderFR.getDistance() + encoderBR.getDistance());
	}
	
	public void reset() {
	    encoderFL.reset();
        encoderFR.reset();
        encoderBL.reset();
        encoderBR.reset();
	}

    public void setPIDSourceType(PIDSourceType pidSource) {
        sourceType = pidSource;
        
    }

    public PIDSourceType getPIDSourceType() {
        return sourceType;
    }

    public double pidGet() {
        return getAverageDistance();
    }
}
