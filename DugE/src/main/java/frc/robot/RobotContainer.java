// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static frc.robot.Constants.FuelConstants.LAUNCHER_SHORT_SHOT;
import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.DashboardConstants.*;

import java.util.Map;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ExampleAuto;
import frc.robot.commands.Intake;
import frc.robot.commands.LaunchSequence;
import frc.robot.commands.TurnToPoint;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

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
    ///// operatorController.rightBumper().whileTrue(new
    // LaunchSequence(fuelSubsystem));
    // While the A button is held on the operator controller, eject fuel back out
    // the intake
    //// operatorController.a().whileTrue(new Eject(fuelSubsystem));
    //// operatorController.povDown().whileTrue(new ClimbDown(climberSubsystem));
    //// operatorController.pov .whileTrue(new ClimbUp(climberSubsystem));
    //// operatorController.getPOV(DRIVER_CONTROLLER_PORT)
    // Set the default command for the drive subsystem to the command provided by
    // factory with the values provided by the joystick axes on the driver
    // controller. The Y axis of the controller is inverted so that pushing the
    // stick away from you (a negative value) drives the robot forwards (a positive
    // value)
    //// driveSubsystem.setDefaultCommand(new arcade(driveSubsystem,
    //// driverController));

    // driveSubsystem.driveArcade(DRIVER_CONTROLLER_PORT, ROTATION_SCALING, false);



    // TODO operatorController.button()

    SmartDashboard.putData("Auto Mode", autoChooser);

    // var layout = Shuffleboard.getTab("Controls")
    //     .getLayout("Controls", BuiltInWidgets.kNetworkTables)
    //     .withSize(3, 6)
    //     .withProperties(Map.of("Label position", "LEFT"));

    // layout.addString("SHORT SHOT", () -> "R1");
    // layout.addString("SET SHOT", () -> "L2");
    // layout.addString("DRIVE SLOW", () -> "THUMB TRIGGER");
    // layout.addString("REVERSE ROTATION", () -> "11");
    // layout.addString("FRONT TO BACK", () -> "4");
    // layout.addString("TURN TO BLUE", () -> "9");
    // layout.addString("TURN TO RED", () -> "10");
    // layout.addString("LONG SHOT", () -> "L1");
    // layout.addString("INTAKE", () -> "CIRCLE");
    // layout.addString("SPIT IT OUT", () -> "TRIANGLE");

    // 1. Get the default NetworkTable instance
NetworkTableInstance inst = NetworkTableInstance.getDefault();

// 2. Create the "Controls" subtable (this creates the tree node)
NetworkTable controlsTable = inst.getTable("Controls");

// 3. Put data into the subtable
// These will appear as children under the "Controls" node in the tree
controlsTable.getEntry("SHORT SHOT").setString("R1");
controlsTable.getEntry("SET SHOT").setString("L2");
controlsTable.getEntry("DRIVE SLOW").setString("THUMB TRIGGER");
controlsTable.getEntry("REVERSE ROTATION").setString("11");
controlsTable.getEntry("FRONT TO BACK").setString("4");
controlsTable.getEntry("TURN TO BLUE").setString("9");
controlsTable.getEntry("TURN TO RED").setString("10");
controlsTable.getEntry("LONG SHOT").setString("L1");
controlsTable.getEntry("INTAKE").setString("CIRCLE");
controlsTable.getEntry("SPIT IT OUT").setString("TRIANGLE");



    SmartDashboard.putData("Short Shot", new  LaunchSequence(fuelSubsystem, () -> SmartDashboard.getNumber(SHORT_SHOT, LAUNCHER_SHORT_SHOT)));
    
    SmartDashboard.putNumber(INDEXER, Constants.FuelConstants.INDEXER_THE_BRAKE);
    SmartDashboard.putNumber(INTAKE, Constants.FuelConstants.INTAKE_INTAKING_PERCENT);
    SmartDashboard.putNumber(SHORT_SHOT, Constants.FuelConstants.LAUNCHER_SHORT_SHOT);
    SmartDashboard.putNumber(LONG_SHOT, Constants.FuelConstants.LAUNCHER_LONG_SHOT);
    SmartDashboard.putNumber(SET_SHOT, Constants.FuelConstants.LAUNCHER_SET_SHOT);
    SmartDashboard.putNumber(SPIT_IT_OUT, Constants.FuelConstants.INTAKE_EJECT_PERCENT);
    SmartDashboard.putNumber(LAUNCHER_IDLE, Constants.FuelConstants.LAUNCHER_IDLE);
    

    driverController.button(DriveConstants.THUMB_TRIGGER)
        .toggleOnTrue(new InstantCommand(() -> driveSubsystem.speedToggle()));

    driverController.button(DriveConstants.DRIVE_REVERSE_ROTATION_BUTTON_ID)
        .toggleOnTrue(new InstantCommand(() -> driveSubsystem.reverseRotation()));
    
    driverController.button(DriveConstants.DRIVE_REVERSE_FRONT_BUTTON_ID)
        .toggleOnTrue(new InstantCommand(() -> driveSubsystem.reverseFront()));
    driverController.button(DriveConstants.TURN_TO_BLUE)
        .whileTrue(new TurnToPoint(driveSubsystem, Constants.FieldConstants.blueHub));
    driverController.button(DriveConstants.TURN_TO_RED)
        .whileTrue(new TurnToPoint(driveSubsystem, Constants.FieldConstants.redHub));

    
   driverController.button(DriveConstants.TRIGGER)
        .whileTrue(new LaunchSequence(fuelSubsystem, 
                       () -> SmartDashboard.getNumber(SHORT_SHOT, LAUNCHER_SHORT_SHOT)));
     
    operatorController.R1()
        .whileTrue(new LaunchSequence(fuelSubsystem, 
        () -> SmartDashboard.getNumber(SHORT_SHOT, LAUNCHER_SHORT_SHOT)));
    
    operatorController.L1().whileTrue(new LaunchSequence(fuelSubsystem,
        () ->  SmartDashboard.getNumber(LONG_SHOT, Constants.FuelConstants.LAUNCHER_LONG_SHOT)));
    
    operatorController.L2().whileTrue(new LaunchSequence(fuelSubsystem,
        () -> SmartDashboard.getNumber(SET_SHOT, Constants.FuelConstants.LAUNCHER_SET_SHOT)));
    
    operatorController.circle().whileTrue(new Intake(fuelSubsystem,
        () -> SmartDashboard.getNumber(INTAKE, Constants.FuelConstants.LAUNCHER_SET_SHOT)));
    
    operatorController.triangle().whileTrue(new InstantCommand(() -> fuelSubsystem
        .spitItOut( SmartDashboard.getNumber(SPIT_IT_OUT, Constants.FuelConstants.INTAKE_EJECT_PERCENT))));


        
    fuelSubsystem.setDefaultCommand(fuelSubsystem
        .run(() -> fuelSubsystem.stopStuffFromGoingInTheShooter(Constants.FuelConstants.INDEXER_THE_BRAKE)));
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
