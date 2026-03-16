// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class upperIntake extends SubsystemBase {
  /** Creates a new upperIntake. */

  private boolean isStalled;
  private boolean isReset;
  private final SparkMax Intake;

  public upperIntake() {

    isStalled = false;
    isReset = false;
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    Intake = new SparkMax(Constants.IntakeConstants.upperIntakeID, MotorType.kBrushless);
    intakeConfig.smartCurrentLimit(Constants.IntakeConstants.INDEXER_MOTOR_CURRENT_LIMIT);
    intakeConfig.inverted(true);
    Intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);




  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
