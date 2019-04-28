package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3504.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class PIDGearBox extends TunablePIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private WPI_TalonSRX driveMaster;
	private WPI_TalonSRX driveSlave;
	
	public PIDGearBox(String name, double kP, double kI, double kD, double f) {
		super(name, kP, kI, kD, f);
	}
	
	public PIDGearBox() {
		this("GearBox", 0.1, 1, 0, 0);
    	
    	driveMaster = new WPI_TalonSRX(RobotMap.DRIVE_MASTER);
		driveSlave = new WPI_TalonSRX(RobotMap.DRIVE_SLAVE);
		
		driveSlave.follow(driveMaster, FollowerType.PercentOutput);
			
     	setInputRange(-1, 1);
    	setPercentTolerance(10);
    	getPIDController().setContinuous(false);
    	
    	//LiveWindow.addActuator(this.toString(), "PID Controller", getPIDController());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return driveMaster.getMotorOutputPercent();
    }
    
    protected void usePIDOutput(double output) {
    	driveMaster.pidWrite(output);
    }
    
    public void stop() {
    	driveMaster.set(0);
    }
}

