package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team3504.robot.RobotMap;

/**
 *
 */
public class Solenoid extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static DoubleSolenoid doubleSolenoid;
	
	public Solenoid() {
		doubleSolenoid = 
				new DoubleSolenoid(RobotMap.SOLENOID_FWD_CHANNEL, RobotMap.SOLENOID_BWD_CHANNEL);
	}
	
	public void openSolenoid() {
		doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeSolenoid() {
		doubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void stop() {
		doubleSolenoid.set(DoubleSolenoid.Value.kOff);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

