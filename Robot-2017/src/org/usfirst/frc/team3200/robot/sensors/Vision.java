package org.usfirst.frc.team3200.robot.sensors;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision {
	
	private AxisCamera cam1;
	
	private VisionThread thread;
	
	public final Object imgLock = new Object();
	
	public double centerX;
	
	VisionRunner.Listener<GripPipeline> listener = (pipeline) -> {
		if (!pipeline.filterContoursOutput().isEmpty()) {
            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            synchronized (imgLock) {
                centerX = r.x + (r.width / 2);
            }
        }
	};
	
	public Vision() {
		cam1 = CameraServer.getInstance().addAxisCamera("Axis Camera 1", "10.32.00.11");
		cam1.setResolution(480, 360);
		
		thread = new VisionThread(cam1, new GripPipeline(), listener);
		thread.start();
	}
	
	public boolean cameraFound() {
		return cam1.isConnected();
	}
}
