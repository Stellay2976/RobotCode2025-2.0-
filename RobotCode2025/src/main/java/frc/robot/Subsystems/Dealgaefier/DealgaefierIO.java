package frc.robot.Subsystems.Dealgaefier;
// This package contains the Dealgaefier subsystem code for the FRC robot.

import org.littletonrobotics.junction.AutoLog;
// The AutoLog annotation is used to automatically log the inputs and outputs of the DealgaefierIO class.

public interface DealgaefierIO 
// The DealgaefierIO class is an interface that defines the methods for interacting with the Dealgaefier subsystem.
{
    @AutoLog
    public static class DealgaefierIOInputs
    // The DealgaefierIOInputs class contains the inputs and outputs of the Dealgaefier subsystem.
    {
        public double deployMotorPositionRotations = 0.0;
        // The deployMotorPositionRotations variable represents the position of the deploy motor in rotations.
        public double deployMotorVelocityRadPerSec = 0.0;
        // The deployMotorVelocityRadPerSec variable represents the velocity of the deploy motor in radians per second.
        public double deployMotorAppliedVolts = 0.0;
        // The deployMotorAppliedVolts variable represents the voltage applied to the deploy motor in volts.
        public double deployMotorCurrentAmps = 0.0;
        // The deployMotorCurrentAmps variable represents the current flowing through the deploy motor in amps.

        public double intakeMotorPositionRotations = 0.0;
        // The intakeMotorPositionRotations variable represents the position of the intake motor in rotations.
        public double intakeMotorVelocityRadPerSec = 0.0;
        // The intakeMotorVelocityRadPerSec variable represents the velocity of the intake motor in radians per second.
        public double intakeMotorAppliedVolts = 0.0;
        // The intakeMotorAppliedVolts variable represents the voltage applied to the intake motor in volts.
        public double intakeMotorCurrentAmps = 0.0;
        // The intakeMotorCurrentAmps variable represents the current flowing through the intake motor in amps.
        public boolean deployed = false;
        // The deployed variable represents whether the Dealgaefier mechanism is deployed or not.
    }

    default void updateInputs(DealgaefierIOInputs inputs) 
    {
        // The updateInputs method is responsible for updating the inputs of the Dealgaefier subsystem.
    }

    public default void setDeployVoltage(double volts) 
    {
        // The setDeployVoltage method is responsible for setting the voltage of the deploy motor.
    }

    public default void setIntakeVoltage(double volts) 
    {
        // The setIntakeVoltage method is responsible for setting the voltage of the intake motor.
    }

    public default void initializeDutyEncoder() 
    {
        // The initializeDutyEncoder method is responsible for initializing the duty cycle encoder of the Dealgaefier subsystem.
        //A duty cycle encoder is a type of encoder that uses a square wave signal to measure the position of a rotating object.
    }

    public default void updateSetpoint(double setpoint) 
    {
        // The updateSetpoint method is responsible for updating the setpoint of the Dealgaefier subsystem.
        //A setpoint is a desired value that a control system tries to achieve.
    }

    public default void deploy() 
    {
        // The deploy method is responsible for deploying the Dealgaefier mechanism.
    }

    public default void retract() 
    {
        // The retract method is responsible for retracting the Dealgaefier mechanism.
    }

    public default void shoot() 
    {
        // The shoot method is responsible for shooting the Dealgaefier mechanism.
    }
}
