package org.usfirst.frc.team3200.robot.sensors;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision implements PIDSource{
	
	private AxisCamera cam1;
	
	private VisionThread thread;
	
	public final Object imgLock = new Object();
	
	public double centerX;
	
	public Rect r1;
	public Rect r2;
	
	private int width = 480;
	private int height = 360;
	
	VisionRunner.Listener<GripPipeline> listener = (pipeline) -> {
		if (!pipeline.filterContoursOutput().isEmpty()) {
            r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
            
            
            synchronized (imgLock) {
                centerX = r1.x + (r1.width / 2);
            }
        }
	};
	
	public Vision() {
		cam1 = CameraServer.getInstance().addAxisCamera("Axis Camera 1", "10.32.00.11");
		cam1.setResolution(width, height);
		
		thread = new VisionThread(cam1, new GripPipeline(), listener);
		thread.start();
	}
	
	public double getXposR1(){
		if(r1!=null){
			return r1.x;
		} else {
			return -1;
		}
	}
	
	public double getXposR2(){
		if(r2!=null){
			return r2.x;
		} else {
			return -1;
		}
	}
	
	public boolean cameraFound() {
		return cam1.isConnected();
	}
	
	public double getSetPoint(){
		return width/2;
	}
	
	public int getHeight(){
		return height;
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
		return (r1.x + r1.width/2 + r2.x + r2.width/2)/2;
	}
}
