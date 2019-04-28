package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3504.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class LimitSwitch extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static WPI_TalonSRX motor;
	private DigitalInput limitSwitch;
	
	public LimitSwitch() {
		motor = new WPI_TalonSRX(RobotMap.MOTOR_PORT);
	    limitSwitch = new DigitalInput(RobotMap.LIMITSWITCH_PORT);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveForward() {
    	motor.set(0.4);	
    }
    
    public void driveBackward() {
    	motor.set(-0.4);
    }
    
    public void stop() {
    	motor.stopMotor();
    }
    
    public boolean isBumped() {
    	return !limitSwitch.get();
    	
    }
}

