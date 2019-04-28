package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3504.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3504.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
/**
 *
 */
public class DriveTrain extends Subsystem {

	private WPI_TalonSRX leftMasterTalon;
	private WPI_TalonSRX leftSlaveTalon1;
	private WPI_TalonSRX leftSlaveTalon2;
	private WPI_TalonSRX rightMasterTalon;
	private WPI_TalonSRX rightSlaveTalon1;
	private WPI_TalonSRX rightSlaveTalon2;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain() {
		leftMasterTalon = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_MASTER_PORT);
		leftSlaveTalon1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_SLAVE_PORT_1);
		leftSlaveTalon2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_SLAVE_PORT_2);
		rightMasterTalon = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_MASTER_PORT);
		rightSlaveTalon1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_SLAVE_PORT_1);
		rightSlaveTalon2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_SLAVE_PORT_2);
		
		leftSlaveTalon1.follow(leftMasterTalon, FollowerType.PercentOutput);
		leftSlaveTalon2.follow(leftMasterTalon, FollowerType.PercentOutput);
		rightSlaveTalon1.follow(rightMasterTalon, FollowerType.PercentOutput);
		rightSlaveTalon2.follow(rightMasterTalon, FollowerType.PercentOutput);
	}
	
	public void forward() {
		leftMasterTalon.set(0.4);
		rightMasterTalon.set(0.4);
	}
	public void backward() {
		leftMasterTalon.set(-0.4);
		rightMasterTalon.set(-0.4);
	}
	public void stop() {
		leftMasterTalon.set(0);
		rightMasterTalon.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

