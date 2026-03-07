// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  
  private final SparkMax Intake;
  private boolean isStalled;
  private boolean isReset;
  private final Timer stallTimer = new Timer();

  public IntakeSubsystem() {
    isStalled = false;
    isReset = false;
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    Intake = new SparkMax(Constants.FuelConstants.INTAKE_MOTOR_ID, MotorType.kBrushless);
    intakeConfig.smartCurrentLimit(Constants.FuelConstants.INDEXER_MOTOR_CURRENT_LIMIT);
    intakeConfig.inverted(true);
    Intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


  }

  public void spitItOut(double im_power)
  {
    setIntakeRoller( im_power );
  }

  public void setIntakeRoller(double power)
  {
    if( isStalled)
    {
      Intake.set(0);  
    }
    else{

    Intake.set(power);
    }
  }

  @Override
  public void periodic() {
    

    if (isStalled)
    {
      checkIsReset();
    }
    else
    {
      checkIsStalled();
    }

  }
  public void checkIsReset(){

    if (isReset)
    {
      if ( stallTimer.get() > 10 )
      {
        isReset = false;
        isStalled = false;
      }
    }
    else
    {
      isReset = true;
      stallTimer.stop();
      stallTimer.reset();
      stallTimer.start();
    }


  }
  public void checkIsStalled() {


    double current = Intake.getOutputCurrent();
    double velocity = Math.abs(Intake.getEncoder().getVelocity());
   
    if ( current >= Constants.FuelConstants.INDEXER_MOTOR_CURRENT_LIMIT && velocity < 10 )
    {
      stallTimer.start();
    }
    else{
      stallTimer.stop();
      stallTimer.reset();
    }
  

    if (stallTimer.get() >= 1.5) {
      isStalled = true;
    } else {
      isStalled = false;
    }

    if (isStalled)
    {
      Intake.set(0);
    }
    // This method will be called once per scheduler run
  }
}
