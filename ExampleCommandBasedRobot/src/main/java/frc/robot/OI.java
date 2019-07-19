/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.print.attribute.standard.JobHoldUntil;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveByDistance;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick drivingStick;
  
  JoystickButton autoTest; 

  public OI(){
    drivingStick = new Joystick(0);

    autoTest = new JoystickButton(drivingStick, 1);
    autoTest.whenPressed(new DriveByDistance(20));
  }

  public double getDrivingStickY(){
    return drivingStick.getY();
  }

  public double getDrivingStickX(){
    return drivingStick.getX();
  }
}
