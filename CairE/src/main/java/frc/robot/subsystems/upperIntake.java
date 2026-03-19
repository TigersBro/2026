// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class upperIntake extends SubsystemBase {
  /** Creates a new upperIntake. */

  private boolean isStalled;
  private final Timer m_stallTimer = new Timer(); // Timer to track stall duration
  private final SparkMax Intake;
  private final SlewRateLimiter m_filter = new SlewRateLimiter(IntakeConstants.intakeSlew);

  public upperIntake() {

    isStalled = false;
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    Intake = new SparkMax(Constants.IntakeConstants.upperIntakeID, MotorType.kBrushless);
    intakeConfig.smartCurrentLimit(Constants.IntakeConstants.INDEXER_MOTOR_CURRENT_LIMIT);
    intakeConfig.inverted(true);
    Intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);




  }

  

  public void Eject(double im_power)
  {
    setIntakeRoller( im_power *-1 );
  }

   public void stop()
  {
    setIntakeRoller(0);
  }

  public void Intake(double im_power)
  {
    setIntakeRoller( im_power );
  }

  public void setIntakeRoller(double power)
  {
    if (isStalled) {
      Intake.set(0);
    } else {
      double filteredPower = m_filter.calculate(power);
      Intake.set(filteredPower);    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   double current = Intake.getOutputCurrent();
    double velocity = Intake.getEncoder().getVelocity();

    if (current > 35.0 && Math.abs(velocity) < 10) {
      isStalled = true;
      m_stallTimer.reset(); // Start counting the 1.5 seconds NOW
    } else if (current < 5.0) {
      // Auto-reset stall once current drops (driver released button)
      isStalled = false;
    }
     if (isStalled && m_stallTimer.hasElapsed(1.5)) {
      isStalled = false;
    }

  }
}
