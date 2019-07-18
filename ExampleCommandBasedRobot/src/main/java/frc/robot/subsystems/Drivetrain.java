/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
// import frc.robot.commands.DriveByJoystick;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax masterLeft;
	private CANSparkMax followerLeft;

	private CANSparkMax masterRight;
	private CANSparkMax followerRight;

  private DifferentialDrive drive;

  private CANEncoder leftEnc;
  private CANEncoder rightEnc;

  private CANPIDController leftPID;
  private CANPIDController rightPID;
  
  public Drivetrain() {
    masterLeft = new CANSparkMax(RobotMap.DRIVE_LEFT_MASTER_PORT, MotorType.kBrushless);
    followerLeft = new CANSparkMax(RobotMap.DRIVE_LEFT_FOLLOWER_PORT, MotorType.kBrushless);

    masterRight = new CANSparkMax(RobotMap.DRIVE_RIGHT_MASTER_PORT, MotorType.kBrushless);
    followerRight = new CANSparkMax(RobotMap.DRIVE_RIGHT_FOLLOWER_PORT, MotorType.kBrushless);

    followerLeft.follow(masterLeft);
    followerRight.follow(masterRight);

    leftEnc = masterLeft.getEncoder();
    rightEnc = masterRight.getEncoder();

    leftPID = masterLeft.getPIDController();
    rightPID = masterRight.getPIDController();

  }

  public void forward() {
    masterLeft.set(0.1);
    masterRight.set(-0.1);
  }
  
  public void backward() {
    masterLeft.set(-0.1); // the negative sign reverses the direction of the motor
    masterRight.set(0.1);
  }
  
  // stops spinning the motor
  public void stop() {
    masterLeft.set(0);
    masterRight.set(0);
  }
  
  public double convertToNative(double position){
    return leftEnc.getPositionConversionFactor() * position; 
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
