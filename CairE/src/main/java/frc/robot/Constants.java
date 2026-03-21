// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.subsystems.upperIntake;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity. 
 */
public final class Constants {

  public static final class DriveConstants {
    // Motor controller IDs for drivetrain motors
    public static final int LEFT_LEADER_ID = 1;
    public static final int LEFT_FOLLOWER_ID = 3;
    public static final int RIGHT_LEADER_ID = 2;
    public static final int RIGHT_FOLLOWER_ID = 4;


    public static final int DRIVE_REVERSE_FRONT_BUTTON_ID = 4;
    public static final int DRIVE_REVERSE_ROTATION_BUTTON_ID = 11;
    public static final int THUMB_TRIGGER = 2;
    public static final int TRIGGER = 1;
    public static final int TURN_TO_BLUE = 9;
    public static final int TURN_TO_RED = 10;
    


    public static final double SLOW_MODE_MOVE = 0.55;
    public static final double SUPER_SLOW_MODE_MOVE = 0.3;
    public static final double SUPER_SLOW_MODE_TURN = 0.3;
    public static final double SLOW_MODE_TURN = 0.65;
    public static final double TURN_MULTIPLIER = .8333;


    public static final double kTrackWidth = 0.6; // TODO Measure distance between left and right wheels (meters).

    // Current limit for drivetrain motors. 60A is a reasonable maximum to reduce
    // likelihood of tripping breakers or damaging CIM motors
    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
  }

  public static final class OperatorConstants {

    // Port constants for driver and operator controllers. These should match the
    // values in the Joystick tab of the Driver Station software
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    // This value is multiplied by the joystick value when rotating the robot to
    // help avoid turning too fast and beign difficult to control
    public static final double DRIVE_SCALING = 1;
    public static final double ROTATION_SCALING = 1;

    public static final int DRIVER_CONTROLLER_PORT0 = 0;
    public static final int OPERATOR_CONTROLLER_PORT1 = 1;
    public static final int TANK_CONTROLLER_PORT2 = 2;
  
  } 

  public static final class IntakeConstants{

    public static final int upperIntakeID = 5;
    public static final int lowerIntakeID = 6;

    public static final double INTAKE_UPPER_PERCENT = .5;
    public static final double INTAKE_LOWER_PERCENT = .5;
    public static final double EJECT_UPPER_PERCENT = .5;
    public static final double EJECT_LOWER_PERCENT = .5;

    public static final int INDEXER_MOTOR_CURRENT_LIMIT = 40;

    public static final double intakeSlew = 3.0;


  }

  public static final class FieldConstants{

    public static final Translation2d blueHub = new Translation2d(5.15, 4.11);
    public static final Translation2d redHub = new Translation2d(12.38, 4.11);
    
  }
  public static final class DashboardConstants{
    public static final String INTAKE_UPPER = "Intake Upper - Circle";
    public static final String INTAKE_LOWER = "Intake Lower - Circle";
    public static final String EJECT_UPPER = "Eject Upper - Triangle";
    public static final String EJECT_LOWER = "Eject Lower - Triangle";
  }
}
