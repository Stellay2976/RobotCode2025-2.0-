package frc.robot.Subsystems.Dealgaefier;

import static frc.lib.SparkUtil.*;

import static frc.robot.subsystems.Dealgaefier.DealgaefierConstants.*;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import org.littletonrobotics.junction.AutoLogOutput;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class DealgaefierIOSpark implements DealgaefierIO
{
    final SparkMax deployMotor = new SparkMax(DealgaefierConstants.kDeployCanID, MotorType.kBrushless);
    final RelativeEncoder deployEncoder = deployMotor.getEncoder();

    final SparkMax intakeMotor = new SparkMax(DealgaefierConstants.kIntakeCanID, MotorType.kBrushless);
    final RelativeEncoder intakeEncoder = intakeMotor.getEncoder();

    public DutyCycleEncoder deployAbsEncoder = new DutyCycleEncoder(DealgaefierConstants.kDeployAbsEncoder);

    private LoggedNetworkNumber tuningP = new LoggedNetworkNumber("/Tuning/Dealgaefier/P", DealgaefierConstants.kProportionalGainSpark);
    private LoggedNetworkNumber tuningD = new LoggedNetworkNumber("/Tuning/Dealgaefier/D", DealgaefierConstants.kDerivativeTermSpark);
    private LoggedNetworkNumber tuningG = new LoggedNetworkNumber("/Tuning/Dealgaefier/G", DealgaefierConstants.kGravityTermSpark);

    @AutoLogOutput (key="Dealgaefier/Setpoint")
    private double absSetpoint;

    private PIDController controller = new PIDController(DealgaefierConstants.kProportionalGainSpark, DealgaefierConstants.kIntegralTermSpark, DealgaefierConstants.kDerivativeTermSpark);

    private SparkMaxConfig deployConfig;

    @AutoLogOutput (key="Dealgaefier/DesiredVoltage")
    private double desiredVoltage = 0.0;

    public boolean absEncoderInitialized = false;

    @AutoLogOutput (key="Dealgaefier/Deployed")
    public boolean deployed = false;

    public DealgaefierIOSpark()
    {
        this.controller.enableContinuousInput(0,1);

        deployConfig = new SparkMaxConfig();
        deployConfig
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(DealgaefierConstants.kCurrentLimit)
            .voltageCompensation(12);
        
        tryUntilOk(
            deployMotor,
            5,
            () -> deployMotor.configure(deployConfig, ResetMode.kResetSafeParameters,
                    PersistMode.kPersistParameters));
        
        var intakeConfig = new SparkMaxConfig().apply(deployConfig);
        intakeConfig.smartCurrentLimit(DealgaefierConstants.kCurrentLimit);

        intakeConfig.inverted(true);
        tryUntilOk(
            intakeMotor,
            5,
            () -> intakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters,
                    PersistMode.kPersistParameters));

        this.controller.setTolearnce(kTolerance); //Doesn't actually do anything unless using: "controller.atSetpoint()"
    }

    @Override
    public void updateInputs(DealgaefierIOInputs inputs)
    {
        ifOk(deployMotor, deployEncoder::getPosition, (value) -> inputs.deployMotorPositionRotations = value);
        ifOk(deployMotor, deployEncoder::getVelocity, (value) -> inputs.deployMotorVelocityRadPerSec = value);
        ifOk(
            deployMotor,
            new DoubleSupplier[] {deployMotor::getAppliedOutput, deployMotor::getBusVoltage},
            (values -> inputs.deployMotorAppliedVolts = values[0] * values[1]));
        ifOk(deployMotor, deployMotor::getOutputCurrent,(value) -> inputs.deployMotorCurrentAmps = value);


        ifOk(intakeMotor, intakeEncoder::getPosition, (value) -> inputs.intakeMotorPositionRotations = value);
        ifOk(intakeMotor, intakeEncoder::getVelocity, (value) -> inputs.intakeMotorVelocityRadPerSec = value);
        ifOk(
            intakeMotor,
            new DoubleSupplier[] {intakeMotor::getAppliedOutput, intakeMotor::getBusVoltage},
            (values -> inputs.deployMotorAppliedVolts = values[0] * values [1]));
        ifOk(intakeMotor, intakeMotor::getOutputCurrent, (value) -> inputs.intakeMotorCurrentAmps = value);

        if(absEncoderInitialized == false)
        {
            initializeDutyEncoder();
        }
    }
}
