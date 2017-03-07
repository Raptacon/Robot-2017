package org.usfirst.frc.team3200.robot.sensors;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3200.robot.Robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class ShooterVision implements VisionRunner.Listener<GripPipeline> {

    private AxisCamera cam2;


    private VisionThread thread2;
    
    public Rect rect1;

    private int width = 320;
    private int height = 240;

    private double fov = 53.0;

    public ShooterVision() {
        
        cam2 = CameraServer.getInstance().addAxisCamera("Fuel Camera", "10.32.00.12");
        cam2.setResolution(width, height);

        thread2 = new VisionThread(cam2, new GripPipeline(), this);

        thread2.start();
    }

    synchronized public double getXposR1() {
        if (rect1 != null) {
            return rect1.x + (rect1.width / 2);
        } else {
            return 0;
        }
    }

    public double getHeightR1() {
        if (rect1 != null) {
            return rect1.height;
        } else {
            return -1;
        }
    }
    
    public boolean rectanglesDetected() {
        return rect1 != null;
    }

    public boolean cameraFound() {
        return cam2.isConnected();
    }

    private double rectPosToAngle(double x) {
        // converts value from range 0, 240 to range -26.5, 26.5
        return x / width * fov - fov / 2;
    }
    
    public double getSetPoint() {
        return Robot.imu.getHeading() + rectPosToAngle(getXposR1()) + 9;
    }

    public int getHeight() {
        return height;
    }

    @Override
    synchronized public void copyPipelineOutputs(GripPipeline pipeline) {
        ArrayList<MatOfPoint> output = pipeline.findContoursOutput();

        rect1 = null;

        if (output != null && output.size() > 0) {
            rect1 = Imgproc.boundingRect(output.get(0));
        }
    }
}
