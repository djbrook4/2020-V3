/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Drive Train
  public static final int DRIVETRAIN_LEFT_FRONT = 1;  //CAN
	public static final int DRIVETRAIN_LEFT_BACK = 3;   //CAN
	public static final int DRIVETRAIN_RIGHT_FRONT = 2; //CAN
  public static final int DRIVETRAIN_RIGHT_BACK = 4;  //CAN

  public static final int INTAKE = 8;  //CAN
  public static final int INDEX = 9;  //CAN


  
  //SHOOTER
  public static final int SHOOTER = 10;        //CAN
  public static final double FRONT_SPEED = .5;  //const
  public static final double REAR_SPEED = .4;   //const
  public static final double DRIVE_SPEED = 1;   //const
  
  //Elevator
  public static final int PIVOT = 12; //CAN
  public static final int ELEVATOR_LEFT = 5;  //CAN
  public static final int ELEVATOR_RIGHT = 6;  //CAN
  public static final double LIFT_SPEED = .6;   //const

  // Joysticks
	public static final int OI_DRIVER_CONTROLLER = 0; //joy1
  public static final int OI_OP_CONTROLLER = 1;     //joy2
  public static final int OI_SQUISHY = 2;           //joy3
  
	public static final int DRIVE_JOY_FORWARD = 1;    //JOY_axis
	public static final int DRIVE_JOY_TURN = 3;       //JOY_axis

	//public static final int OP_LEFTSTICK = 1;     //JOY_axis
  //public static final int OP_RIGHTSTICK = 5;    //JOY_axis

  public static final int OP_XBOX_LEFTSTICK = 1;    //JOY_axis
  public static final int OP_XBOX_RIGHTSTICK = 5;    //JOY_axis

  public static final int SQUISHY_XBOX_LEFTSTICK = 1;    //JOY_axis
  public static final int SQUISHY_XBOX_RIGHTSTICK = 5;    //JOY_axis

  //LED stuff
  public static final int LED_STRING_LENGTH = 300;  //set to whatever the strand ends up being
  public static final int LED_PWM = 8;  //PWM

  //Lemon light stuf
  public static final double KNOWN_DISTANCE = 161.3; //inches
  public static final int PIXEL_WIDTH_KNOWN = 65; //pixels
  public static final double KNOWN_TAPE_BOUND_WIDTH = 39.25; //inches
  public static final double FOCAL_LENGTH = ( KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH;
  public static final double GEAR_RATIO = 10.7/1.0;

  public static final double kTurnP = 1;
  public static final double kTurnI = 0;
  public static final double kTurnD = 0;
  public static final double DISTANCE_PER_PULSE = (6 * Math.PI) / 2048; //inches

  //trajectory constants
  public static final int SHOOTER_HEIGHT = 22; // inches change later
  public static final double GRAVITY = 386.09; // inches/ sec ^2

}
