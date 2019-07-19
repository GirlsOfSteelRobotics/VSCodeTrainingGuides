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
    System.out.println("DbD init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.driveByEnc(encRev);  
    System.out.println("encRev: " + encRev + " --> ");
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("Left Cur Pos: " + Robot.drivetrain.leftEnc.getPosition() + "  Right Cur Pos:" + Robot.drivetrain.rightEnc.getPosition());
    System.out.println("Left Enc Error: " + (encRev - Robot.drivetrain.leftEnc.getPosition()) + "  Right Enc Error: " + (encRev + Robot.drivetrain.rightEnc.getPosition()));
    if ((Math.abs(encRev - Robot.drivetrain.leftEnc.getPosition()) < 1) && (Math.abs(encRev + Robot.drivetrain.rightEnc.getPosition()) < 1))
      return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
    System.out.println("DbD end");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
