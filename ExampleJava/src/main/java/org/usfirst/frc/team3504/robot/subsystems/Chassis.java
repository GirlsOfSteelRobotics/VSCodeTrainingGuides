package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3504.robot.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;//added
import org.usfirst.frc.team3504.robot.commands.*;

import com.ctre.phoenix.motorcontrol.NeutralMode; //added
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; //added
import com.ctre.phoenix.motorcontrol.FollowerType; //added

/**
 *
 */
public class Chassis extends Subsystem {
	
	private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER);
	private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER);
	private WPI_TalonSRX leftFollowA = new WPI_TalonSRX(RobotMap.LEFT_FOLLOW_A);
	private WPI_TalonSRX leftFollowB = new WPI_TalonSRX(RobotMap.LEFT_FOLLOW_B);
	private WPI_TalonSRX rightFollowA = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOW_A);
	private WPI_TalonSRX rightFollowB = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOW_B);
	
	private DifferentialDrive drive;
	

	public Chassis() {
		
		leftMaster.setNeutralMode(NeutralMode.Brake);
    	leftFollowA.setNeutralMode(NeutralMode.Brake);
    	leftFollowB.setNeutralMode(NeutralMode.Brake);
    	
    	rightMaster.setNeutralMode(NeutralMode.Brake);
    	rightFollowA.setNeutralMode(NeutralMode.Brake);
    	rightFollowB.setNeutralMode(NeutralMode.Brake);
    	
    	leftMaster.setSafetyEnabled(false);
    	leftFollowA.setSafetyEnabled(false);
    	leftFollowB.setSafetyEnabled(false);
    	
    	rightMaster.setSafetyEnabled(false);
    	rightFollowA.setSafetyEnabled(false);
    	rightFollowB.setSafetyEnabled(false);
    	
    	rightFollowA.follow(rightMaster, FollowerType.PercentOutput);
    	rightFollowB.follow(rightMaster, FollowerType.PercentOutput);
    	
    	leftFollowA.follow(leftMaster, FollowerType.PercentOutput);
    	leftFollowB.follow(leftMaster, FollowerType.PercentOutput);
    	
    	drive = new DifferentialDrive(leftMaster, rightMaster);
    	drive.setSafetyEnabled(false);
    	drive.setDeadband(0.02);
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DriveByArcade());
    }
    
    public void driveByArcade(double speed, double rotation) {
    	drive.arcadeDrive(speed, rotation);
    }
    
    public void stop() {
    	drive.stopMotor();
    }
    
}

