// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1 extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public Auto1(IntakeSubsystem i_intake, CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // ballSubsystem.setFeederRoller(-.3);
    // ballSubsystem.setIntakeRoller(.4);
    // ballSubsystem.setLauncherRoller(.2);

    addCommands(
    // Drive backwards for .25 seconds. The driveArcadeAuto command factory
    // intentionally creates a command which does not end which allows us to control
    // the timing using the withTimeout decorator
    //new AutoDrive(driveSubsystem,0.5,  0.0).withTimeout(3),
    // Spin up the launcher for 1 second and then launch balls for 9 seconds, for a
    // total of 10 seconds

    // driveSubsystem.driveArcade(() -> 0.5, () -> 0).withTimeout(.25),
    //     // Stop driving. This line uses the regular driveArcade command factory so it
    //     // ends immediately after commanding the motors to stop
    //     driveSubsystem.driveArcade(() -> 0, () -> 0),
    new AutoDrive(driveSubsystem, .25,0).withTimeout(1),

    new ParallelCommandGroup( 
      new Intake(i_intake, () -> Constants.FuelConstants.INTAKE_AUTO_PERCENT), 
      new LaunchSequence(ballSubsystem, () -> Constants.FuelConstants.LAUNCHER_SHORT_SHOT).withTimeout(8)//,
      
      )

   //new Zero(ballSubsystem).withTimeout(.5)
    
    );
    

  }
}
