/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3504.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3504.robot.commands.ClosePneumatic;
import org.usfirst.frc.team3504.robot.commands.OpenPneumatic;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick Joy = new Joystick(RobotMap.Joystick); 
	Button button1 = new JoystickButton(Joy, 1); //Open Pneumatic 
	Button button2 = new JoystickButton(Joy, 2); //Close Pneumatic

	public OI () 
	{
		button1.whenPressed(new OpenPneumatic());
		button2.whenPressed(new ClosePneumatic());
	}

	
}
