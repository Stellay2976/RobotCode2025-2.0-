package frc.robot.Subsystems.Dealgaefier;
// This package contains the Dealgaefier subsystem code for the FRC robot.

import static frc.robot.subsystems.Dealgaefier.DealgaefierConstants.*;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class DealgaefierIOSim implements DealgaefierIO
// The DealgaefierIOSim class is a simulation implementation of the DealgaefierIO interface.
// It provides simulated behavior for the Dealgaefier subsystem, allowing for testing and development without physical hardware.
// The DealgaefierIOSim class implements the empty methods we defined in the DealgaefierIO interface, providing simulated inputs and outputs for the Dealgaefier subsystem.
{
    private DCMotorSim deployMotor = new DCMotorSim
    // The deployMotor variable is an instance of the DCMotorSim class, which simulates the behavior of a DC motor.
    // It is initialized with a linear system representing the deploy motor of the Dealgaefier subsystem.
    (
        LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1), 0.004, 1/DealgaefierConstants.kDeployGearRatio), 
        DCMotor.getNEO(1)
    );
    // The DCMotorSim constructor takes a linear system and a motor model as parameters.
    // The linear system is created using the LinearSystemId.createDCMotorSystem method, which takes a motor model, a time constant, and a gear ratio as parameters.
    // The motor model is obtained using the DCMotor.getNEO method, which returns a model of a NEO motor.
    // The time constant is set to 0.004 seconds, and the gear ratio is obtained from the DealgaefierConstants class.
    //The gear ration is implemented as a fraction because the gear ratio is defined as the ratio of the number of teeth on the driven gear to the number of teeth on the driving gear.


    private DCMotorSim intakeMotor = new DCMotorSim
    // The intakeMotor variable is an instance of the DCMotorSim class, which simulates the behavior of a DC motor.
    // It is initialized with a linear system representing the intake motor of the Dealgaefier
    (
        LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1), 0.004, 1/DealgaefierConstants.kIntakeGearRatio),
        DCMotor.getNEO(1)
    );
    // The DCMotorSim constructor takes a linear system and a motor model as parameters.
    // The linear system is created using the LinearSystemId.createDCMotorSystem method, which takes a motor model, a time constant, and a gear ratio as parameters.
    // The motor model is obtained using the DCMotor.getNEO method, which returns a model of a NEO motor.
    // The time constant is set to 0.004 seconds, and the gear ratio is obtained from the DealgaefierConstants class.
    //The gear ration is implemented as a fraction because the gear ratio is defined as the ratio of the number of teeth on the driven gear

    private LoggedNetworkNumber tuningP = new LoggedNetworkNumber("Tuning/Dealgaefier/P", DealgaefierConstants.kProportionalTermSim);
    // The tuningP variable is an instance of the LoggedNetworkNumber class, which allows for logging and tuning of the proportional gain for the PID control of the Dealgaefier mechanism in simulation.
    private LoggedNetworkNumber tuningD = new LoggedNetworkNumber("Tuning/Dealgaefier/D", DealgaefierConstants.kDerivativeTermSim);
    // The tuningD variable is an instance of the LoggedNetworkNumber class, which allows for logging and tuning of the derivative gain for the PID control of the Dealgaefier mechanism in simulation.

    private PIDController deployController = new PIDController(tuningP.get(), 0.0, tuningD.get());
    // The deployController variable is an instance of the PIDController class, which implements a PID control algorithm for the deploy mechanism of the Dealgaefier subsystem.
    // It is initialized with the proportional gain, integral gain, and derivative gain obtained from the tuningP and tuningD LoggedNetworkNumber instances.
    // The integral gain is set to 0.0, as it is not used in this implementation.

    private double deployAppliedVolts = 0.0;
    // The deployAppliedVolts variable represents the voltage applied to the deploy motor in volts. It is initialized to 0.0 volts.
    private double intakeAppliedVolts = 0.0;
    // The intakeAppliedVolts variable represents the voltage applied to the intake motor in volts. It is initialized to 0.0 volts.

    @AutoLogOutput(key="Dealgaefier/Setpoint")
    private double deploySetpoint = 0.0;
    // The deploySetpoint variable represents the desired position of the deploy mechanism in rotations. 
    // It is initialized to 0.0 rotations and is annotated with @AutoLogOutput to automatically log its value to the network tables.
    // The @AutoLogOutput annotation allows for easy monitoring and tuning of the deploy setpoint during simulation.

    @Override
    public void updateInputs(DealgaefierIOInputs inputs)
    // The updateInputs method is responsible for updating the inputs of the Dealgaefier subsystem in simulation.
    {
        if (tuningP.get() != deployController.getP() || tuningD.get() != deployController.getD())
        {
            deployController.setPID(tuningP.get(), 0.0, tuningD.get());
        }
        // This block checks if the tuning values for the proportional and derivative gains have changed.
        // If they have changed, it updates the PID controller with the new values.
        // This allows for real-time tuning of the PID controller during simulation.

        double absoluteRotations = this.deployMotor.getAngularPositionRotations() * DealgaefierConstants.kDeployGearRatio;
        // The absoluteRotations variable represents the absolute position of the deploy mechanism in rotations.
        // It is calculated by multiplying the angular position of the deploy motor (in rotations) by the gear ratio of the deploy mechanism.
        // This conversion accounts for the mechanical advantage provided by the gear ratio, allowing for accurate representation of the deploy mechanism's position.

        deployMotor.setInputVoltage(deployController.calculate(absoluteRotations));
        intakeMotor.setInputVoltage(intakeAppliedVolts);
        // The setInputVoltage method is called on both the deployMotor and intakeMotor instances to apply the calculated voltages to the motors.
        // For the deployMotor, the voltage is calculated using the PID controller based on the current absolute position of the deploy mechanism.
        //This is because the deploy mechanism requires precise control to reach and maintain the desired position, which is achieved through the PID control algorithm.
        // For the intakeMotor, the voltage is set directly from the intakeAppliedVolts variable.

        deployMotor.update(0.02);
        intakeMotor.update(0.02);
        // The update method is called on both the deployMotor and intakeMotor instances to simulate the passage of time and update the state of the motors based on the applied voltages.
        // The parameter 0.02 represents the time step in seconds, which corresponds to a 20 ms update interval, commonly used in FRC robot code.

        inputs.deployMotorPositionRotations = deployMotor.getAngularPositionRotations();
        // Gets the deploy motor's current position and stores it in the inputs.
        inputs.deployMotorVelocityRadPerSec = deployMotor.getAngularVelocityRadPerSec();
        // Gets the deploy motor's current rotational speed and stores it in the inputs.
        inputs.deployMotorAppliedVolts = deployAppliedVolts;
        // Stores the voltage currently being applied to the deploy motor.
        inputs.deployMotorCurrentAmps = deployMotor.getCurrentDrawAmps();
        // Gets how much current the deploy motor is drawing and stores it in the inputs.

        inputs.intakeMotorPositionRotations = intakeMotor.getAngularPositionRotations();
        // Gets the intake motor's current position and stores it in the inputs.
        inputs.intakeMotorVelocityRadPerSec = intakeMotor.getAngularVelocityRadPerSec();
        // Gets the intake motor's current rotational speed and stores it in the inputs.
        inputs.intakeMotorAppliedVolts = intakeAppliedVolts;
        // Stores the voltage currently being applied to the intake motor.
        inputs.intakeMotorCurrentAmps = intakeMotor.getCurrentDrawAmps();
        // Gets how much current the intake motor is drawing and stores it in the inputs.
    }

    @Override
    public void setDeployVoltage(double volts)
    // Sets voltage for Deploy Motor
    {
        deployAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
        // Limits the voltage to a safe range within -12V and 12V
        // requested voltage → clamp to safe range → apply/store voltage
    }

    @Override
    public void setIntakeVoltage(double volts)
    // Sets voltage for Intake Motor
    {
        intakeAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
        // Limits the voltage to a safe range within -12V and 12V
        // requested voltage → clamp to safe range → apply/store voltage
    }

    @Override
    public void updateSetpoint(double rotations)
    // Updates the desired position of the deploy mechanism.
    {
        this.deploySetpoint = rotations;
        deployController.setSetpoint(this.deploySetpoint);
        // Tells the deploy motor's controller to move toward the new setpoint
        // desired position → save it → tell controller to move there.
    }
}
