/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveByDistance extends Command {

  double inputDist;
  double encRev; 
  double error; 

  private CANSparkMax leftSpark;
  private CANSparkMax rightSpark;

  public DriveByDistance(double dist) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    inputDist = dist; 
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    encRev = Robot.drivetrain.convertToNative(inputDist); 
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.pidCont.setReference(encRev, ControlType.kPosition);  
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("Enc Error:" + (encRev - Robot.drivetrain.leftEnc.getPosition()));
    if (Robot.drivetrain.leftEnc.getPosition() >= encRev)
      return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
