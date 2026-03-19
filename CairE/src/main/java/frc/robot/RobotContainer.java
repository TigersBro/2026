// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//67 

import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.DashboardConstants.*;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Auto1;
import frc.robot.commands.EjectCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.lowerIntake;
import frc.robot.subsystems.upperIntake;

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
  private final upperIntake upperIntake = new upperIntake();
  private final lowerIntake lowerIntake = new lowerIntake();
  // private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  // TODO add when we have a climber

  // The driver's controller

  private final CommandJoystick driverController = new CommandJoystick(OperatorConstants.DRIVER_CONTROLLER_PORT0);

  // The operator's controller, by default it is setup to use a single controller
  private final CommandPS5Controller operatorController = new CommandPS5Controller(
      OPERATOR_CONTROLLER_PORT);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    if (!Preferences.containsKey("UpperIntakeSpeed")) {
    Preferences.setDouble("UpperIntakeSpeed", 0.8);
  }
    if (!Preferences.containsKey("LowerIntakeSpeed")) {
    Preferences.setDouble("LowerIntakeSpeed", 0.7);
  }
     if (!Preferences.containsKey("UpperEjectSpeed")) {
    Preferences.setDouble("UpperEjectSpeed", 0.8);
  }
    if (!Preferences.containsKey("LowerEjectSpeed")) {
    Preferences.setDouble("LowerEjectSpeed", 0.7);
  }
    
    
    configureBindings();

    


    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    autoChooser.setDefaultOption("Autonomous", new Auto1(lowerIntake, driveSubsystem, upperIntake));
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

    // HOLD Circle (Regular) -> Intake
  operatorController.circle().and(operatorController.L1().negate())
    .whileTrue(new IntakeCommand(upperIntake, lowerIntake));

  // HOLD Circle + L1 (Modifier) -> Eject
  operatorController.circle().and(operatorController.L1())
    .whileTrue(new EjectCommand(upperIntake, lowerIntake));


    SmartDashboard.putData("Auto Mode", autoChooser);

    // 1. Get the default NetworkTable instance
NetworkTableInstance inst = NetworkTableInstance.getDefault();

// 2. Create the "Controls" subtable (this creates the tree node)
NetworkTable controlsTable = inst.getTable("Controls");

// 3. Put data into the subtable
// These will appear as children under the "Controls" node in the tree
controlsTable.getEntry("DRIVE SLOW").setString("THUMB TRIGGER");
controlsTable.getEntry("REVERSE ROTATION").setString("11");
controlsTable.getEntry("FRONT TO BACK").setString("4");
controlsTable.getEntry("TURN TO BLUE").setString("9");
controlsTable.getEntry("TURN TO RED").setString("10");
controlsTable.getEntry("INTAKE").setString("CIRCLE");
controlsTable.getEntry("SPIT IT OUT").setString("TRIANGLE");

    
   // SmartDashboard.putNumber(INTAKE_UPPER, Constants.IntakeConstants.INTAKE_UPPER_PERCENT);
   // SmartDashboard.putNumber(INTAKE_LOWER, Constants.IntakeConstants.INTAKE_LOWER_PERCENT);

   // SmartDashboard.putNumber(EJECT_UPPER, Constants.IntakeConstants.EJECT_UPPER_PERCENT);
   // SmartDashboard.putNumber(EJECT_LOWER, Constants.IntakeConstants.EJECT_LOWER_PERCENT);
    

   
    /// If this doesn't work....try commenting in the next line
    driveSubsystem.setDefaultCommand(driveSubsystem.run(() -> driveSubsystem.driveArcade(
        driverController.getY(),
        driverController.getZ(),
        true)));

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
