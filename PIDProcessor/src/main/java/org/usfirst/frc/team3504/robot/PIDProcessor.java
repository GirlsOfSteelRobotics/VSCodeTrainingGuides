package org.usfirst.frc.team3504.robot;
//this will need to change depending on where you put it
import java.util.ArrayList;

public class PIDProcessor {
	private double kP;
	private double kI;
	private double kD;
	private ArrayList<Double> prevPoints; // data points to compute derivative
	private double target;
	private double outputThreshold; // min output to cauase actual movement
	private double errorThreshold; // error tolerance
	private double integral; // cumulative integral value
	private boolean done; // arrived and start to count-down to finish
	private long countdownStartTime; // count-down time between arrival and finish (due to effect of slipping)
	private long waitTime = 500; // count-down time
	private boolean stall; // under outputThreshold but above errorThreshold
	private long stallTime = 2000; // time to wait until decide to force-finish when in stall
	private double integralCap; // max integral contribution
	
	
	public PIDProcessor(double kP, double kI, double kD, double target) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.target = target;
		this.done = false;
		this.stall = false;
	}

	public void setThreshold(double outputThreshold, double errorThreshold){
		this.outputThreshold = outputThreshold;
		this.errorThreshold = outputThreshold;
		this.integralCap = 2*outputThreshold;
	}
	
	public double outputNormal(double input){
		double error = target - input;
		prevPoints.add(0,error);
		int sampleLength = (prevPoints.size() > 5) ? 5 : prevPoints.size(); // the first several points
		double dt = sampleLength * 1.0;
		double prevVal = (prevPoints.size() > 5) ? prevPoints.get(4): prevPoints.get(prevPoints.size()-1);
		double dError_dt = (error-prevVal)/dt;
		if (prevPoints.size() > 1 && ((prevPoints.get(1)*error) < 0)){ // if error switching sign, integral starts back from 0
			integral = 0;
		}
		integral += error;
		double integralValue = (Math.abs(integral*kI) > 0.4) ? 0.4*((integral)/Math.abs(integral)): integral*kI;
		double output = kP*error + integralValue + kD*dError_dt;
		return output;
	}
	
	public double outputRotate(double input){
		double error;
  	if (target > 180.0){
  		error = (input < (target - 180.0)) ? (360.0 - target + input) : (target - input);
  	}
  	else {
  		error = (input > (target + 180.0)) ? (360.0 + target - input) : (target - input);
  	}
		prevPoints.add(0,error);
		int sampleLength = (prevPoints.size() > 5) ? 5 : prevPoints.size();
		double dt = sampleLength * 1.0;
		double prevVal = (prevPoints.size() > 5) ? prevPoints.get(4): prevPoints.get(prevPoints.size()-1);
		double dError_dt = (error-prevVal)/dt;
		if (prevPoints.size() > 1 && ((prevPoints.get(1)*error) < 0)){
			integral = 0;
		}
		integral += error;
		double integralValue = (Math.abs(integral*kI) > integralCap) ? integralCap*((integral)/Math.abs(integral)): integral*kI;
		double output = kP*error + integralValue + kD*dError_dt;
		if (!done){
			checkArrived(output,error);
		}
		return output;
	}
	
	public void checkArrived(double output, double error){
		if (output < this.outputThreshold){
			if (error < this.errorThreshold){
			this.done = true;
			this.countdownStartTime = System.currentTimeMillis();
			}
			else{
				this.stall = true;
				this.countdownStartTime = System.currentTimeMillis();				
		}}
		}
	
	public boolean checkDone(){
		if (stall){
			return ((System.currentTimeMillis()-this.countdownStartTime > stallTime));
		}
		else if (done){
			return ((System.currentTimeMillis()-this.countdownStartTime > waitTime));
		}
		else {
			return false;
		}
	}
	
}
