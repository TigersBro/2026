// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.DashboardConstants.LAUNCHER_IDLE;
import static frc.robot.Constants.FuelConstants.*;
@Logged
public class CANFuelSubsystem extends SubsystemBase {
  private final SparkMax LeftLauncher;
  private final SparkMax RightLauncher;
  private final SparkMax Feeder;
  private final SparkMax Intake;
  private double idleSpeed; 
  /** Creates a new CANBallSubsystem. */
  public CANFuelSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    LeftLauncher = new SparkMax(LEFT_INTAKE_LAUNCHER_MOTOR_ID, MotorType.kBrushless);
    RightLauncher = new SparkMax(RIGHT_INTAKE_LAUNCHER_MOTOR_ID, MotorType.kBrushless);
    Feeder = new SparkMax(INDEXER_MOTOR_ID, MotorType.kBrushless);
    Intake = new SparkMax(Constants.FuelConstants.INTAKE_MOTOR_ID, MotorType.kBrushless);
    // create the configuration for the feeder roller, set a current limit and apply
    // the config to the controller
    SparkMaxConfig feederConfig = new SparkMaxConfig();
    feederConfig.smartCurrentLimit(INDEXER_MOTOR_CURRENT_LIMIT);
    Feeder.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    feederConfig.inverted(true);
    Intake.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkMaxConfig launcherConfig = new SparkMaxConfig();

    launcherConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);
    launcherConfig.voltageCompensation(12);
    launcherConfig.idleMode(IdleMode.kCoast);
    RightLauncher.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    launcherConfig.inverted(true);
    LeftLauncher.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    idleSpeed = SmartDashboard.getNumber(LAUNCHER_IDLE, Constants.FuelConstants.LAUNCHER_IDLE);
    SmartDashboard.putData("Left Launcher", new Sendable() {
    @Override
    public void initSendable(SendableBuilder builder) {
        // Essential: Mark as a motor controller for the slider widget
        builder.setSmartDashboardType("Motor Controller");
        
        // Control: The slider uses the "Value" property
        builder.addDoubleProperty("Value", LeftLauncher::get,null);

        // Telemetry: Add extra fields you want to monitor
        builder.addDoubleProperty("Velocity", () -> LeftLauncher.getEncoder().getVelocity(), null);
        builder.addDoubleProperty("Position", () -> LeftLauncher.getEncoder().getPosition(), null);
        builder.addDoubleProperty("Temperature", LeftLauncher::getMotorTemperature, null);
        builder.addDoubleProperty("Bus Voltage", LeftLauncher::getBusVoltage, null);
    }
});


  }

  // A method to set the voltage of the intake roller
  public void setLauncherRoller(double power) {
//TODO apply limelight distance math here.

    LeftLauncher.set(power);
    RightLauncher.set(power); // positive for shooting
  }

  public void spitItOut(double im_power)
  {
    setIntakeRoller( im_power );
    setFeederRoller(im_power);
  }

  public void setIntakeRoller(double power)
  {
    Intake.set(power);
  }
  // A method to set the voltage of the intake roller
  public void setFeederRoller(double power) {
    Feeder.set(power); // positive for shooting
  }
  public void stopStuffFromGoingInTheShooter (double power)
  {
    Feeder.set(power);
    //possible TODO...add a timer or loop  here which checks the idleSpeed to see if it has changed periodically 
    LeftLauncher.set(idleSpeed);
    RightLauncher.set(idleSpeed);
  }
  // A method to stop the rollers
  public void stop() {
    Feeder.set(0);
    LeftLauncher.set(0);
    RightLauncher.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
