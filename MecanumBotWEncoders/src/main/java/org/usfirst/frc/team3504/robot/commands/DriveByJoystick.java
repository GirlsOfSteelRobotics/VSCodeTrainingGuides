package org.usfirst.frc.team3504.robot.commands;

import org.usfirst.frc.team3504.robot.Robot;
import org.usfirst.frc.team3504.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;			

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class DriveByJoystick extends Command {

    public DriveByJoystick() {
        requires(Robot.mecanumChassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//make sure that every time the sensors start at zero
    	Robot.mecanumChassis.getLeftBack().setSelectedSensorPosition(0, 0, 10);
    	Robot.mecanumChassis.getLeftFront().setSelectedSensorPosition(0, 0, 10);
    	Robot.mecanumChassis.getRightBack().setSelectedSensorPosition(0, 0, 10);
    	Robot.mecanumChassis.getRightFront().setSelectedSensorPosition(0, 0, 10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.mecanumChassis.getLeftBack().configOpenloopRamp(.1, 10);
    	Robot.mecanumChassis.getLeftFront().configOpenloopRamp(.1, 10);
    	Robot.mecanumChassis.getRightBack().configOpenloopRamp(.1, 10);
    	Robot.mecanumChassis.getRightFront().configOpenloopRamp(.1, 10);
    	//this is using the logitech controller
    	double y = Robot.oi.getMecanumDrive().getY(); //forward/backward
    	double x = Robot.oi.getMecanumDrive().getX(); //rotation
    	double z = Robot.oi.getMecanumDrive().getZ(); //strafe
    	
    	//This is when the gears are a one to one ratio and the 8 is there
    	//for a multiplier (can adjust as was not tested)
    	// DO NOT CHANGE HOW THE X Y AND Z VALUES ARE SET
    	Robot.mecanumChassis.getLeftBack().set(ControlMode.Velocity, 
    			RobotMap.CODES_PER_WHEEL * 8 * (-z + y + x));
    	Robot.mecanumChassis.getLeftFront().set(ControlMode.Velocity, 
    			RobotMap.CODES_PER_WHEEL * 8 * (z + y + x));
    	Robot.mecanumChassis.getRightBack().set(ControlMode.Velocity, 
    			RobotMap.CODES_PER_WHEEL * 8 * (z + y - x));
    	Robot.mecanumChassis.getRightFront().set(ControlMode.Velocity, 
    			RobotMap.CODES_PER_WHEEL * 8 * (-z + y - x));
    	
    	//debugging print statements
    	/*System.out.println("post LB: " + 
    			Robot.mecanumChassis.getLeftBack().getSelectedSensorPosition(0));
    	System.out.println("post RB: " + 
    			Robot.mecanumChassis.getRightBack().getSelectedSensorPosition(0));
    	System.out.println("post LF: " + 
    			Robot.mecanumChassis.getLeftFront().getSelectedSensorPosition(0));
    	System.out.println("post RF: " + 
    			Robot.mecanumChassis.getRightFront().getSelectedSensorPosition(0));
    	
    	System.out.println("y: " + y + " x: " + x + " z: " + z); */
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumChassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
