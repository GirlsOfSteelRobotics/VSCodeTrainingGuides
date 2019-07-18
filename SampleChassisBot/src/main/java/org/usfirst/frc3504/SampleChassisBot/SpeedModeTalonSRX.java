package org.usfirst.frc3504.SampleChassisBot;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SpeedModeTalonSRX extends CANSparkMax {
    private int topSpeed;

    public SpeedModeTalonSRX(int talonID, int speed) {
        super(talonID, MotorType.kBrushless);
        topSpeed = speed;
    }

    @Override
    public void set(double speedPct) {
        super.set(speedPct/4.0);
    }
}