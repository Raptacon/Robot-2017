package org.usfirst.frc.team3200.robot.sensors;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class IMU {

    private I2C imu;

    private double headingOffset = 0;

    public IMU() {
        imu = new I2C(I2C.Port.kOnboard, 0x28);

        // flip y and z axes
        // imu.write(0x42, 0x3);

        // set to measurement mode
        imu.write(0x3D, 0x8);
    }

    public double address() {
        return readAddressByte(0x00);
    }

    public void reset() {
        imu.write(0x3D, 0x1C);
        imu.write(0x3D, 0x8);
    }

    private byte readAddressByte(int address) {
        byte[] buffer = new byte[1];
        imu.read(address, 1, buffer);
        return buffer[0];
    }

    private short readAddressShort(int address) {
        byte[] buffer = new byte[2];
        imu.read(address, 2, buffer);
        short low = (short) (buffer[0] & 0xFF);
        short high = (short) ((buffer[1] << 8) & 0xFF00);
        return (short) (low | high);
    }

    public double getHeading() {
        // 1 degree per 16 raw
        return readAddressShort(0x1A) / 16.0;
    }

    public double getUniversalHeading() {
        return (getHeading() - headingOffset + 360) % 360;
    }

    public double getRoll() {
        return readAddressShort(0x1C) / 16.0;
    }

    public double getPitch() {
        return readAddressShort(0x1E) / 16.0;
    }

    public double getAccelX() {
        // 1 meter per second squared per 100 raw
        return readAddressShort(0x28) / 100.0;
    }

    public double getAccelY() {
        return readAddressShort(0x2A) / 100.0;
    }

    public void setUniversalHeading(double heading) {
        headingOffset = getHeading() - heading;
    }

    public PIDSource pidHeading = new PIDSource() {

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {

        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return getHeading();
        }

    };
}