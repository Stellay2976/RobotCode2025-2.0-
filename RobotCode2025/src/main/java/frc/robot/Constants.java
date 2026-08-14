package frc.robot; 
// This package contains the main robot code for the FRC robot.

public class Constants 
// Contains robot-wide numerical or boolean constants. 
// This class should not be used for any other purpose. 
// All constants should be declared globally (i.e. public static). 
// Do not put anything functional in this class.
{
    public static final class OIConstants
    // Operator Interface Constants
    //OI = Operator Interface
    // This class contains constants related to the operator interface, such as joystick axes and button mappings.
    {
        public static final int kDriveYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.4;
    }

    public static final class DIO{} // Digital Input/Output Constants


    public static final Mode simMode = Mode.SIM; // Simulation mode constant
    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode; 
    // Current mode constant
    // The current mode is determined based on whether the robot is running in real hardware or simulation.
    // If the robot is running in real hardware, the current mode is set to REAL.
    // If the robot is running in simulation, the current mode is set to SIM.
    // This allows for different behavior in the code depending on the mode the robot is running in.
    
    // The Mode enum defines the different modes the robot can be in.
    public static enum Mode
    {
        REAL, //This mode is used when the robot is running in real hardware.

        SIM, //This mode is used when the robot is running in a physicssimulation.

        REPLAY //Replay of a log file.
    }

}