package frc.robot.Subsystems.Dealgaefier;
// This package contains the Dealgaefier subsystem code for the FRC robot.

public class DealgaefierConstants 
// The DealgaefierConstants class is a placeholder for any constants that may be needed for the Dealgaefier subsystem in the future.
{
    public static final boolean kTuningMode = true;
    // The kTuningMode constant is a boolean value that can be used to enable or disable tuning mode for the Dealgaefier subsystem.

    public static final int kDeployCanID = 54;
    // The kDeployCanID constant is an integer value that represents the CAN ID of the deploy motor controller for the Dealgaefier subsystem.
    public static final int kIntakeCanID = 55;
    // The kIntakeCanID constant is an integer value that represents the CAN ID of the intake motor controller for the Dealgaefier subsystem.

    public static final double kRestAbsoluteRotations = 0.22;
    // The kRestAbsoluteRotations constant is a double value that represents the absolute rotations of the Dealgaefier mechanism when it is in the rest position.
    //This can be calculated by taking the absolute rotations of the mechanism when it is in the rest position and subtracting the absolute rotations of the mechanism when it is in the deployed position.
    //You can find the absolute rotations of the mechanism by using an encoder or a potentiometer to measure the position of the mechanism and then converting that position to absolute rotations.
    public static final double kDeployedAbsoluteRotations = -0.075;
    // The kDeployedAbsoluteRotations constant is a double value that represents the absolute rotations of the Dealgaefier mechanism when it is in the deployed position.
    //This can be calculated by taking the absolute rotations of the mechanism when it is in the deployed position and subtracting the absolute rotations of the mechanism when it is in the rest position.
    //You can find the absolute rotations of the mechanism by using an encoder or a potentiometer to measure the position of the mechanism and then converting that position to absolute rotations.

    public static final int kDeployAbsEncoder = 5;
    // The kDeployAbsEncoder constant is an integer value that represents the ID of the absolute encoder used to measure the position of the Dealgaefier mechanism when it is in the deployed position.

    // The following constants are used for the PID control of the Dealgaefier mechanism using a Spark Max motor controller.
    public static final double kProportionalGainSpark = 10.0;
    // The kProportionalGainSpark constant is a double value that represents the proportional gain for the PID control of the Dealgaefier mechanism.
    public static final double kIntegralTermSpark = 0.0;
    // The kIntegralTermSpark constant is a double value that represents the integral term for the PID control of the Dealgaefier mechanism.
    public static final double kDerivativeTermSpark = 1.0;
    // The kDerivativeTermSpark constant is a double value that represents the derivative term for the PID control of the Dealgaefier mechanism.
    public static final double kGravityTermSpark = -0.6;
    // The kGravityTermSpark constant is a double value that represents the gravity term for the PID control of the Dealgaefier mechanism.

    //These constants are used for the PID control of the Dealgaefier mechanism in simulation.
    public static final double kProportionalTermSim = 0.1;
    // The kProportionalTermSim constant is a double value that represents the proportional term for the PID control of the Dealgaefier mechanism in simulation.
    public static final double kDerivativeTermSim = 0.0;
    // The kDerivativeTermSim constant is a double value that represents the derivative term for the PID control of the Dealgaefier mechanism in simulation.

    // The following constants are used for the gear ratios of the Dealgaefier mechanism.
    public static final double kDeployGearRatio = 1;
    // The kDeployGearRatio constant is a double value that represents the gear ratio of the deploy mechanism of the Dealgaefier subsystem.
    //This can be found by taking the number of teeth on the driven gear and dividing it by the number of teeth on the driving gear.
    public static final double kIntakeGearRatio = 1;
    // The kIntakeGearRatio constant is a double value that represents the gear ratio of the intake mechanism of the Dealgaefier subsystem.
    //This can be found by taking the number of teeth on the driven gear and dividing it by the number of teeth on the driving gear.

    // The following constants are used for the speed of the Dealgaefier mechanism.
    public static final double kIntakeSpeed = 0.5;
    // The kIntakeSpeed constant is a double value that represents the speed of the intake mechanism of the Dealgaefier subsystem.
    //This can be found by taking the maximum speed of the motor and multiplying it by the gear ratio of the mechanism.

    // The following constants are used for the current limits of the Dealgaefier mechanism.
    public static final boolean kInverted = false;
    // The kInverted constant is a boolean value that represents whether the motor controller for the Dealgaefier mechanism is inverted or not.
    //Inverted means that the motor controller will reverse the direction of the motor when a positive voltage is applied.
    //When false, the motor controller will run the motor in the normal direction when a positive voltage is applied.
    //Measurement Unit = Amperes (amps).
    public static final int kCurrentLimit = 40;
    // The kCurrentLimit constant is an integer value that represents the current limit for the motor controller of the Dealgaefier mechanism.
    //This can be found by looking at the specifications of the motor controller and setting the current limit to a value that is safe for the motor and the mechanism.
    //Measurement Unit = Amperes (amps).
    public static final double kTolerance = 0.001;
    // The kTolerance constant is a double value that represents the tolerance for the PID control of the Dealgaefier mechanism.
    //This can be found by looking at the specifications of the mechanism and setting the tolerance to a value that is acceptable for the application.
    //Measurement Unit = radians (rads).
    public static final int kDeployCurrentLimit = 40;
    // The kDeployCurrentLimit constant is an integer value that represents the current limit for the deploy motor controller of the Dealgaefier mechanism.
    //This can be found by looking at the specifications of the motor controller and setting the current limit to a value that is safe for the motor and the mechanism.
    //Measurement Unit = Amperes (amps).
    public static final int kIntakeCurrentLimit = 20;
    // The kIntakeCurrentLimit constant is an integer value that represents the current limit for the intake motor controller of the Dealgaefier mechanism.
    //This can be found by looking at the specifications of the motor controller and setting the current limit to a value that is safe for the motor and the mechanism.
    //Measurement Unit = Amperes (amps).
    public static final double kAbsEncoderOffset = -0.5791;
    // The kAbsEncoderOffset constant is a double value that represents the offset for the absolute encoder of the Dealgaefier mechanism.
    //This can be found by measuring the position of the mechanism when it is in the rest position and subtracting the absolute rotations of the mechanism when it is in the rest position.
    //Measurement Unit = radians (rads).
}
