package org.usfirst.frc.team3504.robot.subsystems;

import org.usfirst.frc.team3504.robot.RobotMap;
import org.usfirst.frc.team3504.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 *
 */
public class MecanumChassis extends Subsystem {

	private final WPI_TalonSRX rightFront = RobotMap.mecanumChassisTalonSRX3;
    private final WPI_TalonSRX rightBack = RobotMap.mecanumChassisTalonSRX4;
    private final WPI_TalonSRX leftFront = RobotMap.mecanumChassisTalonSRX1;
    private final WPI_TalonSRX leftBack = RobotMap.mecanumChassisTalonSRX2;

    private MecanumDrive m_drive;
    
    public MecanumChassis() {
    	m_drive = new MecanumDrive(leftFront,leftBack,rightFront,rightBack);
    	
    	//if needed, invert talons
    	//rightFront.setInverted(true);
    	
    	rightFront.setNeutralMode(NeutralMode.Brake);
    	leftFront.setNeutralMode(NeutralMode.Brake);
    	rightBack.setNeutralMode(NeutralMode.Brake);
    	leftBack.setNeutralMode(NeutralMode.Brake);
    	
    	rightFront.setSafetyEnabled(false);
    	leftFront.setSafetyEnabled(false);
    	leftBack.setSafetyEnabled(false);
    	rightBack.setSafetyEnabled(false);
    	
    	//PID
    	setFPID(rightFront);
    	setFPID(rightBack);
    	setFPID(leftFront);
    	setFPID(leftBack);

    	//invert or reverse the motors here
    	
    	m_drive.setSafetyEnabled(false);
    	m_drive.setDeadband(0.04);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveByJoystick());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveByMecanum(double ySpeed, double xSpeed, double zRotation) {
    	m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }
    
    
    public void stop() {
    	m_drive.stopMotor();
    }
    
    public WPI_TalonSRX getLeftFront() {
    	return leftFront;
    }
    
    public WPI_TalonSRX getRightFront() {
    	return rightFront;
    }
    
    public WPI_TalonSRX getLeftBack() {
    	return leftBack;
    }
    
    public WPI_TalonSRX getRightBack() {
    	return rightBack;
    }
    
    public void setFPID(WPI_TalonSRX motor) {
    	//change these to your own motors, 
    	//really only the second value needs to be changed
    	motor.config_kF(0, 0, 10); //sensor slot (dont change), value, time in ms(don't change)
    	motor.config_kP(0, .1, 10);
    	motor.config_kI(0, 0, 10);
    	motor.config_kD(0, 0, 10);
    }
}

