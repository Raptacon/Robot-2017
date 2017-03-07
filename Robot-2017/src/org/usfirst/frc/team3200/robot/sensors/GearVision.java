package org.usfirst.frc.team3200.robot.sensors;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class GearVision implements VisionRunner.Listener<GripPipeline> {

    long oldTime = 0;
    long currentTime;

    private AxisCamera cam1;

    private VisionThread thread1;
    
    public Rect rect1;
    public Rect rect2;

    private int width = 320;
    private int height = 240;

    private double fov = 53.0;

    public GearVision() {
        cam1 = CameraServer.getInstance().addAxisCamera("Gear Camera", "10.32.00.11");
        cam1.setResolution(width, height);

        thread1 = new VisionThread(cam1, new GripPipeline(), this);
        
        thread1.start();
    }

    synchronized public double getXposR1() {
        if (rect1 != null) {
            return rect1.x + (rect1.width / 2);
        } else {
            return -1;
        }
    }

    synchronized public double getXposR2() {
        if (rect2 != null) {
            return rect2.x + (rect2.width / 2);
        } else {
            return -1;
        }
    }
    
    public boolean rectanglesDetected() {
        return rect1 != null;
    }

    public boolean cameraFound() {
        return cam1.isConnected();
    }

    private double rectPosToAngle(double x) {
        // converts value from range 0, 320 to range -26.5, 26.5
        return x / width * fov - fov / 2;
    }

    public double getSetPoint() {
        return Robot.imu.getHeading() + rectPosToAngle((getXposR1() + getXposR2()) / 2);
    }

    @Override
    synchronized public void copyPipelineOutputs(GripPipeline pipeline) {
        ArrayList<MatOfPoint> output = pipeline.findContoursOutput();

        rect1 = null;
        rect2 = null;

        if (output != null && output.size() > 1) {
            rect1 = Imgproc.boundingRect(output.get(0));
            rect2 = Imgproc.boundingRect(output.get(1));
        }
    }
}
