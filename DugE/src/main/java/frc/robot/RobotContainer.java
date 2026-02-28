// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import static frc.robot.Constants.OperatorConstants.*;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.Drive;
import frc.robot.commands.Eject;
import frc.robot.commands.ExampleAuto;
import frc.robot.commands.Intake;
import frc.robot.commands.LaunchSequence;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  private final CANFuelSubsystem fuelSubsystem = new CANFuelSubsystem();
  private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

  // The driver's controller
  
  private final CommandJoystick driverController = new CommandJoystick(OperatorConstants.DRIVER_CONTROLLER_PORT0);

  // The operator's controller, by default it is setup to use a single controller
  private final PS5Controller operatorController = new PS5Controller(
      OPERATOR_CONTROLLER_PORT);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureBindings();

    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    autoChooser.setDefaultOption("Autonomous", new ExampleAuto(driveSubsystem, fuelSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the {@link Trigger#Trigger(java.util.function.BooleanSupplier)}
   * constructor with an arbitrary predicate, or via the named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
   * for {@link CommandXboxController Xbox}/
   * {@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // While the left bumper on operator controller is held, intake Fuel
   //// operatorController.leftBumper().whileTrue(new Intake(fuelSubsystem));
    // While the right bumper on the operator controller is held, spin up for 1
    // second, then launch fuel. When the button is released, stop.
   ///// operatorController.rightBumper().whileTrue(new LaunchSequence(fuelSubsystem));
    // While the A button is held on the operator controller, eject fuel back out
    // the intake
  ////  operatorController.a().whileTrue(new Eject(fuelSubsystem));
   //// operatorController.povDown().whileTrue(new ClimbDown(climberSubsystem));   
   //// operatorController.pov .whileTrue(new ClimbUp(climberSubsystem));
  ////  operatorController.getPOV(DRIVER_CONTROLLER_PORT)
    // Set the default command for the drive subsystem to the command provided by
    // factory with the values provided by the joystick axes on the driver
    // controller. The Y axis of the controller is inverted so that pushing the
    // stick away from you (a negative value) drives the robot forwards (a positive
    // value)
   //// driveSubsystem.setDefaultCommand(new arcade(driveSubsystem, driverController));
   

   ///If this doesn't work....try commenting in the next line
    driveSubsystem.setDefaultCommand(driveSubsystem.run(() -> driveSubsystem.driveArcade(
                                                                                          driverController.getY(),
                                                                                          driverController.getZ() ,
                                                                                          true 
                                                                                         ) 
                                                       )  
                                    ) ;

                                                                                         /*
    driveSubsystem.setDefaultCommand(driveSubsystem.driveArcadeSupplier( 
                                                                          () -> -driverController.getY(),
                                                                          () -> -driverController.getZ() ,
                                                                          () -> true 
                                                                        )
                                    );
*/



    fuelSubsystem.setDefaultCommand(fuelSubsystem.run(() -> fuelSubsystem.stop()));

    climberSubsystem.setDefaultCommand(climberSubsystem.run(() -> climberSubsystem.stop()));
    
    driverController.button(DriveConstants.THUMB_TRIGGER).toggleOnTrue(new InstantCommand( () -> driveSubsystem.speedToggle() ));

    //driverController.triangle().toggleOnTrue(new InstantCommand( () -> driveSubsystem.reverseFront() ));
  
    driverController.button(DriveConstants.DRIVE_REVERSE_ROTATION_BUTTON_ID).toggleOnTrue(new InstantCommand( () -> driveSubsystem.reverseRotation() ));
    driverController.button(DriveConstants.DRIVE_REVERSE_FRONT_BUTTON_ID).toggleOnTrue(new InstantCommand( () -> driveSubsystem.reverseFront() ));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }
}
