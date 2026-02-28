// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANDriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurnToPoint extends Command {
    private final CANDriveSubsystem drivetrain;
    private final Translation2d target;
    private final PIDController turnPID;
  /** Creates a new TurnToPoint. */
  public TurnToPoint( CANDriveSubsystem i_drivetrain, Translation2d target) {
    // Use addRequirements() here to declare subsystem dependencies.
        this.drivetrain = i_drivetrain;
        this.target = target;
        addRequirements(drivetrain);

        turnPID = new PIDController(0.015, 0, 0.001);
        turnPID.enableContinuousInput(-Math.PI, Math.PI);
        turnPID.setTolerance(Math.toRadians(2));
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
           

        Pose2d pose = drivetrain.getPose();
        double deltaX = target.getX() - pose.getX();
        double deltaY = target.getY() - pose.getY();
        double desiredAngle = Math.atan2(deltaY, deltaX);
        double turnOutput = turnPID.calculate(pose.getRotation().getRadians(), desiredAngle);
        drivetrain.tankDrive(-turnOutput, turnOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

    drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
