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

public class lowerIntake extends SubsystemBase {
  /** Creates a new lowerIntake. */
  private boolean isStalled;
  private boolean isReset;
    private final SparkMax Intake;


  public lowerIntake() {

    isStalled = false;
    isReset = false;
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    Intake = new SparkMax(Constants.IntakeConstants.lowerIntakeID, MotorType.kBrushless);
    intakeConfig.smartCurrentLimit(Constants.IntakeConstants.INDEXER_MOTOR_CURRENT_LIMIT);
    intakeConfig.inverted(true);
    Intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);






  }
  public void Eject(double im_power)
  {
    setIntakeRoller( im_power *-1 );
  }

  public void Intake(double im_power)
  {
    setIntakeRoller( im_power );
  }

  public void setIntakeRoller(double power)
  {
   // if( isStalled)
    {
      Intake.set(0);  
    }
    // else{

    // Intake.set(power *-1);
    // }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
