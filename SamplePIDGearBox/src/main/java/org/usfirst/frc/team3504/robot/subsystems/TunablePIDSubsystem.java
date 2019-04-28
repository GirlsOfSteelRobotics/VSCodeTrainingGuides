package org.usfirst.frc.team3504.robot.subsystems;

//need to figure out network tables
import org.usfirst.frc.team3504.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public abstract class TunablePIDSubsystem extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static String name;
	private double kP;
	private double kI;
	private double kD;
	private double f;
	
	private NetworkTableEntry kP_e;
	private NetworkTableEntry kI_e;
	private NetworkTableEntry kD_e;
	private NetworkTableEntry f_e;
	
	
	public TunablePIDSubsystem(String name, double kP, double kI, double kD, double f) {
		super(name, kP, kI, kD, f);
	  	
		this.name = name;
    	this.kP = kP;
    	this.kI = kI;
    	this.kD = kD;
    	this.f = f;
    	
    	kP_e.setDouble(kP);
 		kI_e.setDouble(kI);
 		kD_e.setDouble(kD);
 		f_e.setDouble(f);
    	
    	//printPIDValues();
	}

	public void updatePIDValues(/*NetworkTable table*/) {
		this.kP = SmartDashboard.getNumber("kP", 0);
		this.kI = SmartDashboard.getNumber("kI", 0);
		this.kD = SmartDashboard.getNumber("kD", 0);
		this.f = SmartDashboard.getNumber("f", 0);
		
		//kP_e = table.getEntry("kP");
		//kI_e = table.getEntry("kI");
		//kD_e = table.getEntry("kD");
		//f_e = table.getEntry("f");
		
		setPID();
	}
	    
	public void printPIDValues(/*NetworkTable table*/) {
		SmartDashboard.putNumber("kP", kP);
 		SmartDashboard.putNumber("kI", kI);
 		SmartDashboard.putNumber("kD", kD);
 		SmartDashboard.putNumber("f", f);
 		
 		kP_e.setDouble(kP);
 		kI_e.setDouble(kI);
 		kD_e.setDouble(kD);
 		f_e.setDouble(f);
 		
 	}
	
    public void setPID() {
		getPIDController().setPID(kP, kI, kD, f);
	}
    
    public void printInfo() {
    	SmartDashboard.putNumber("error", getPIDController().getError());
    	//What?!
    	
    	//Robot.table.putNumber("error", getPIDController().getError());
    	System.out.println(this.returnPIDInput() + "  " + getPIDController().getError()
    			+ "   "  + getPIDController().onTarget());
    }
    
    public String toString() {
    	return name;
    }
    
    abstract public void stop();
}

