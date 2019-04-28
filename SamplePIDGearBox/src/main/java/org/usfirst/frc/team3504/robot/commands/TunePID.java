package org.usfirst.frc.team3504.robot.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team3504.robot.Robot;
import org.usfirst.frc.team3504.robot.subsystems.TunablePIDSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public class TunePID<T extends TunablePIDSubsystem> extends Command {

	private ArrayList<Double> values;
	private ArrayList<Double> times;
	private T PIDSubsystem;
	
	private NetworkTableInstance inst = NetworkTableInstance.getDefault();
	private NetworkTable table = inst.getTable("PID");
	private NetworkTableEntry timeout; //check this!!!!!!!!!
	
	public final static Logger log = Logger.getLogger(TunePID.class.getName());	
	
	public TunePID (T PIDSubsystem) {
    	this.requires(PIDSubsystem);
		log.setLevel(Level.ALL);
		this.PIDSubsystem = PIDSubsystem;
    }

    protected void initialize() {
    	log.info("init");
    	
    	values = new ArrayList<Double>();
    	times = new ArrayList<Double>();
    	
    	PIDSubsystem.updatePIDValues(/*Robot.table*/);
    	
    	//check this!!!!!!!
    	double time = timeout.getDouble(0.0);

    	this.setTimeout(time);
		log.info("running");
    }

    protected void execute() {
    	PIDSubsystem.setSetpoint(50);
    	PIDSubsystem.enable();
    	
    	double value = PIDSubsystem.getPIDController().getError();
    	values.add(value);
    	
    	double time = timeSinceInitialized();
    	times.add(time);
    	
    	PIDSubsystem.printInfo();
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	log.info("done");
    	PIDSubsystem.disable();
    	PIDSubsystem.stop();
    	
    	Double[] valuesArray = values.toArray(new Double[values.size()]);
    	//Robot.table.putNumberArray("values", valuesArray);
    	
    	Double[] timesArray = times.toArray(new Double[times.size()]);
    	//Robot.table.putNumberArray("times", timesArray);
		
    	PIDSubsystem.printPIDValues(/*table*/);
    	
    	//table.putBoolean("doneCommand", true);
		log.info("" + PIDSubsystem.onTarget());
    }    	

    protected void interrupted() {

    }
}