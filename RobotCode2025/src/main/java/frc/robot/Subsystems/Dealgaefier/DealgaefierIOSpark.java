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
}
