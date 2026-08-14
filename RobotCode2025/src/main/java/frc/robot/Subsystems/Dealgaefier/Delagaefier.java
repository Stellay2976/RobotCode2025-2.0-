package frc.robot.Subsystems.Dealgaefier;
// This package contains the Dealgaefier subsystem code for the FRC robot.

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Delagaefier extends SubsystemBase
// The Delagaefier class represents the Dealgaefier subsystem of the robot.
// It extends the SubsystemBase class, which provides a base implementation for subsystems in the WPILib command-based framework.
// The Delagaefier subsystem is responsible for controlling the Dealgaefier mechanism on the robot.
{  
    private final Dealgaefier IO io;
    // The IO object is responsible for handling the input and output of the Dealgaefier subsystem.
    private final DealgaefierInputsAutoLogged inputs = new DelagaefierInputsAutoLogged();
    // The inputs object is responsible for logging the inputs of the Dealgaefier subsystem automatically.

    public Dealgaefier(Dealgaefier io)
    {
        this.io = io;
        // The constructor initializes the IO object with the provided Dealgaefier instance.
        //The "this" keyword is used to refer to the current instance of the Delagaefier class.
        //"this.io" refers to the instance variable "io" of the Delagaefier class, while "io" refers to the parameter passed to the constructor.
    }

    @Override
    //@Override annotation indicates that the periodic method is overriding a method from the superclass (SubsystemBase).
    // The periodic method is called periodically by the WPILib framework.
    // It updates the inputs of the Dealgaefier subsystem and logs them using the Logger class.
    public void periodic()
    {
        io.updateInputs(inputs);
        Logger.processInputs("Dealgaefier", inputs); 
    }

    public boolean getDeployed()
    {
        return inputs.deployed;
        // The getDeployed method returns the current state of the Dealgaefier mechanism (deployed or not).
        // It retrieves the value from the inputs object, which is updated in the periodic method.
    }

    public Command setDeployVoltageCommand(double volts) 
    // The setDeployVoltageCommand method returns a command that sets the deploy voltage of the Dealgaefier mechanism.
    {
        return Commands.run(() -> io.setDeployVoltage(volts), this).finallyDo(interrupted -> io.setDeployVoltage(0));
        // The command runs the setDeployVoltage method of the IO object with the specified voltage.
        // The command is associated with the Delagaefier subsystem (this) and will stop the deploy voltage when the command ends or is interrupted.
        // The finallyDo method is used to specify an action to be performed when the command ends or is interrupted.
        // In this case, it sets the deploy voltage to 0 when the command ends or is interrupted.
        // The lambda expression () -> io.setDeployVoltage(volts) is used to define the action to be performed when the command is executed.
    }

    public Command setIntakeVoltageCommand(double volts) 
    // The setIntakeVoltageCommand method returns a command that sets the intake voltage of the Dealgaefier mechanism.
    {
        return Commands.run(() -> io.setIntakeVoltage(volts), this).finallyDo(end -> io.retract());
        // The command runs the setIntakeVoltage method of the IO object with the specified voltage.
        // The command is associated with the Delagaefier subsystem (this) and will retract the mechanism when the command ends.
        // The finallyDo method is used to specify an action to be performed when the command ends or is interrupted.
        // In this case, it calls the retract method of the IO object when the command ends.
        // The lambda expression () -> io.setIntakeVoltage(volts) is used to define the action to be performed when the command is executed.
        // The lambda expression end -> io.retract() is used to define the action to be performed when the command ends.
        // The parameter "end" is not used in this case, but it is required by the finallyDo method.
        // The finallyDo method is called when the command ends, regardless of whether it was interrupted or completed normally.
        // This ensures that the retract method is called to safely retract the mechanism when the command ends.
    }

    public Command deployCommand() {
        return Commands.run(() -> io.deploy(), this).finallyDo(interrupted -> io.setIntakeVoltage(0));
    }

    public Command shootCommand() {
        return Commands.run(() -> io.shoot(), this).finallyDo(interrupted -> io.setIntakeVoltage(0));
    }

    public Command retractCommand() {
        return Commands.run(() -> io.retract(), this).finallyDo(interrupted -> io.setIntakeVoltage(0));
    }

    public void setDeployVoltage(double volts) {
        io.setDeployVoltage(volts);
    }

    public void setIntakeVoltage(double volts) {
        io.setIntakeVoltage(volts);
    }
}