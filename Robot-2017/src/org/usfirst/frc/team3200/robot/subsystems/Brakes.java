package org.usfirst.frc.team3200.robot.subsystems;

import org.usfirst.frc.team3200.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Brakes extends Subsystem {
    DoubleSolenoid brakes;
    public Brakes(){
        brakes = new DoubleSolenoid(RobotMap.BRAKE2, RobotMap.BRAKE1);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void set(boolean value){
        if(value == true){
            brakes.set(DoubleSolenoid.Value.kReverse);
        }else{ 
            brakes.set(DoubleSolenoid.Value.kForward);
        }
        
    }
    
    public boolean get() {
        return (brakes.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void toggle(){
        if(brakes.get()== DoubleSolenoid.Value.kForward){
            set(true);
        }else{
            set(false);
        }
        
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

