/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3504.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static WPI_TalonSRX mecanumChassisTalonSRX1 = new WPI_TalonSRX(1);
    public static WPI_TalonSRX mecanumChassisTalonSRX2 = new WPI_TalonSRX(2);
    public static WPI_TalonSRX mecanumChassisTalonSRX3 = new WPI_TalonSRX(3);
    public static WPI_TalonSRX mecanumChassisTalonSRX4 = new WPI_TalonSRX(4);
    
    //there are 256 encoders ticks per wheel, if using a quadencoder, have to
    //multiply the number by 4 in order to get the correct amount of ticks.
    public static final double CODES_PER_WHEEL = 256.0 * 4.0;
    public static final double WHEEL_DIAMETER = 6.0;
    
}
