// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

import edu.wpi.first.math.geometry.Translation2d;

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
    public static final int LEFT_FOLLOWER_ID = 2;
    public static final int RIGHT_LEADER_ID = 3;
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

  public static final class FuelConstants {
    // Motor controller IDs for Fuel Mechanism motors
    public static final int LEFT_INTAKE_LAUNCHER_MOTOR_ID = 9;
    public static final int RIGHT_INTAKE_LAUNCHER_MOTOR_ID = 10;
    public static final int INTAKE_MOTOR_ID = 7;
    public static final int INDEXER_MOTOR_ID = 8;

    // Current limit for fuel mechanism motors.
    public static final int INDEXER_MOTOR_CURRENT_LIMIT = 40;
    public static final int LAUNCHER_MOTOR_CURRENT_LIMIT = 40;

    // All values likely need to be tuned based on your robot
    public static final double INDEXER_LAUNCHING = 0.6;
    public static final double INDEXER_THE_BRAKE = -.2;
    public static final double INDEXER_SPIT = -.6;

    public static final double INTAKE_INTAKING_PERCENT = 0.6;
    public static final double LAUNCHING_LAUNCHER_PERCENT = .85;
    public static final double INTAKE_EJECT_PERCENT = -0.6;

    public static final double LAUNCHER_SHORT_SHOT =.2;
    public static final double LAUNCHER_IDLE =0;
    public static final double LAUNCHER_LONG_SHOT =.7;
    public static final double LAUNCHER_SET_SHOT =.5;
    public static final double SPIN_UP_SECONDS = 0.6;
  
    public static final NavigableMap<Double, Double> DISTANCE_TO_VOLTAGE_MAP;

    static {
        TreeMap<Double, Double> map = new TreeMap<>();
        // Distance (Key) -> Voltage (Value)
        map.put(.5,1.0);
        map.put(1.0,1.2);
        map.put(1.5,1.5);
        map.put(2.0,1.7);
        map.put(2.5,1.9);
        map.put(3.0,2.0);
        map.put(3.5,2.2);
        map.put(4.0,2.4);
        map.put(4.5,2.6);
        map.put(5.0,2.8);
        map.put(5.5,3.0);
        map.put(6.0,3.2);
        map.put(6.5,3.4);
        map.put(7.0,3.6);
        map.put(7.5,3.8);
        map.put(8.0,4.0);
        map.put(8.5,4.2);
        map.put(9.0,4.4);
        map.put(9.5,4.6);
        map.put(10.0,4.8);
        map.put(10.5,5.0);
        map.put(11.0,5.2);
        map.put(11.5,5.4);

        DISTANCE_TO_VOLTAGE_MAP = Collections.unmodifiableNavigableMap(map);
    }
  }

  public static final class ClimbConstants {
    // Motor controller IDs for Climb motor
    public static final int CLIMBER_MOTOR_ID = 9;

    // Current limit for climb motor
    public static final int CLIMBER_MOTOR_CURRENT_LIMIT = 40;
    // Percentage to power the motor both up and down
    public static final double CLIMBER_MOTOR_DOWN_PERCENT = -0.8;
    public static final double CLIMBER_MOTOR_UP_PERCENT = 0.8;
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
  public static final class FieldConstants{
    

    public static final Translation2d blueHub = new Translation2d(5.15, 4.11);
    public static final Translation2d redHub = new Translation2d(12.38, 4.11);
    
  }
  public static final class DashboardConstants{
    public static final String SET_SHOT = "Set Shot - L2";
    public static final String SHORT_SHOT = "Short Shot - R1";
    public static final String LONG_SHOT = "Long Shot - L1";
    public static final String INTAKE = "Intake - Circle";
    public static final String SPIT_IT_OUT = "Spit it Out - Triangle";
    public static final String INDEXER = "Indexer Prelaunch";
    public static final String INDEXER_LAUNCH = "Indexer Launching";
    public static final String LAUNCHER_IDLE = "Launcher Idle Speed";
  }
}
