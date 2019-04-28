package org.usfirst.frc.team3504.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3504.robot.Robot;
import org.usfirst.frc.team3504.robot.RobotMap;
import org.usfirst.frc.team3504.robot.commands.DriveByJoyStick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FollowerType;

public class Chassis extends Subsystem {

	private WPI_TalonSRX masterLeft;
	private WPI_TalonSRX slaveLeft_A;
	private WPI_TalonSRX slaveLeft_B;
	private WPI_TalonSRX masterRight;
	private WPI_TalonSRX slaveRight_A;
	private WPI_TalonSRX slaveRight_B;

	private DifferentialDrive drive;

	public Chassis() {
		masterLeft = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_MASTER_PORT);
		slaveLeft_A = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_SLAVE_PORT_1);
		slaveLeft_B = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_SLAVE_PORT_2);
		masterRight = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_MASTER_PORT);
		slaveRight_A = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_SLAVE_PORT_1);
		slaveRight_B = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_SLAVE_PORT_2);
		
		masterLeft.setNeutralMode(NeutralMode.Brake);
		slaveLeft_A.setNeutralMode(NeutralMode.Brake);
		slaveLeft_B.setNeutralMode(NeutralMode.Brake);
		masterRight.setNeutralMode(NeutralMode.Brake);
		slaveRight_A.setNeutralMode(NeutralMode.Brake);
		slaveRight_B.setNeutralMode(NeutralMode.Brake);
		
		slaveLeft_A.follow(masterLeft, FollowerType.PercentOutput);
		slaveLeft_B.follow(masterLeft, FollowerType.PercentOutput);
		slaveRight_A.follow(masterRight, FollowerType.PercentOutput);
		slaveRight_B.follow(masterRight, FollowerType.PercentOutput);
		
		drive = new DifferentialDrive(masterLeft, masterRight);
		drive.setSafetyEnabled(true);
		drive.setExpiration(0.1);
		drive.setMaxOutput(1.0);
	}
	

	public void initDefaultCommand() {
		setDefaultCommand(new DriveByJoystick());
	}

	public void driveByJoystick(double yDir, double xDir){
		SmartDashboard.putString("driveByJoystick?", yDir + "," + xDir);
		drive.arcadeDrive(yDir,xDir);
	}
	public void stop() {
		drive.stopMotor();
	}

}