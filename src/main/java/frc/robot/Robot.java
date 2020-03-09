/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.sql.Driver;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import frc.robot.Subsystems.*;
import frc.robot.Commands.*;

import edu.wpi.first.cameraserver.CameraServer;


import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.utils.Limelight;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static DriveTrain driveSub;
  //public static Shooter shooterSub;
  public static PivotCan pivotSub;
  public static Elevator elevatorSub;
  public static Intake intakeSub;
  public static LED LEDcontrol;
  public static Index indexSub;
  public static Shooter shooterSub;

  public static ArcadeDrive arcadeCmd;

  public static FixedDrive fixedDriveCmd;
  public static FixedDriveStop fixedDriveStopCmd;


  private long initTime;

  /*----------------------------------
  EXAMPLE SUBSYTEM SETUP
  */
  //public static ExampleSub expSub;

  /*
  public WPI_TalonFX _leftMaster = driveSub._leftMaster;
  public WPI_TalonFX _rightMaster = driveSub._rightMaster;
  public WPI_TalonFX  _leftFollow = driveSub._leftFollow;
  public WPI_TalonFX  _rightFollow = driveSub._rightFollow;

  DifferentialDrive _drive = driveSub._drive;
  */


  //CANSparkMax Pivot = new CANSparkMax(6, MotorType.kBrushless);

  

  public static OI m_oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //Pivot.restoreFactoryDefaults();
    //Pivot.getEncoder(EncoderType.kHallSensor, 4096);

    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);


    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);

    SmartDashboard.putData("Auto choices", m_chooser);
    System.out.println("robotinit");



     // PWM port 9
    // Must be a PWM header, not MXP or DIO
    //m_led = new AddressableLED(9);

    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    //m_ledBuffer = new AddressableLEDBuffer(300);
    //m_led.setLength(m_ledBuffer.getLength());

    // Set the data
    //m_led.setData(m_ledBuffer);
    //m_led.start();  
    driveSub = new DriveTrain();
    pivotSub = new PivotCan();
    LEDcontrol = new LED();
    elevatorSub = new Elevator();
    intakeSub = new Intake();
    indexSub = new Index();
    shooterSub = new Shooter();


    
    /*----------------------------------
    EXAMPLE SUBSYTEM INIT
    */
    //expSub = new ExampleSub();

    //ALWAYS LAST!!!!!!!!!!!!!!!!!!!!!!!!!!
    m_oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // SmartDashboard.putString("i'm working", "ok");
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tlong = table.getEntry("tlong");

//read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double schlong = tlong.getDouble(0.0);

//post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    SmartDashboard.putNumber("schlong", schlong);

    SmartDashboard.putNumber("distance", Limelight.getDistance());

    SmartDashboard.putNumber("hor distance", Limelight.getHorDistance());




    // Fill the buffer with a rainbow
    //rainbow();
    // Set the LEDs
    //m_led.setData(m_ledBuffer);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();

    driveSub._drive.setSafetyEnabled(false);

    pivotSub.update(-.15);
    Timer.delay(.25);
    pivotSub.update(0);

    System.out.println("moveBackward");
    driveSub.arcadeDrive(-0.35,0, false);
    Timer.delay(1.75);
    driveSub.arcadeDrive(0,0, false);

    System.out.println("moveForward");
    driveSub.arcadeDrive(0.35,0, false);
    Timer.delay(1.5);
    driveSub.arcadeDrive(0,0, false);

    
    System.out.println("shooterStart");
    shooterSub.update(-0.69); //-0.67
    Timer.delay(1.5);
       
    System.out.println("indexStart");
    indexSub.update(0.75);
    Timer.delay(3);

    System.out.println("cancel");
    shooterSub.update(0);
    indexSub.update(0);
    driveSub.arcadeDrive(0,0, false);

    System.out.println(":) done");

  }


  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //System.out.println("run teleop");
    /*
    m_oi.RunBack.whenPressed(new InstantCommand() {
      @Override
      protected void execute(){
      Robot.driveSub.arcadeDrive(-.5, 0);
      Timer.delay(1.4);
      Robot.driveSub.arcadeDrive(0, 0);
      end();
      }
    });
    */



   

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }

  
  /*
  public void pidDrive() {
    final double kP = 0; //change as needed (Speed)
    final double kI = 0; //change as needed
    final double kD = 0; //change as needed

    double setPoint = 10; //change as needed
    double errorSum = 0; //change as needed
    double lastTimestamp = 0; //change as needed
    double lastError = 0; //change as needed


    double sensorPos = (((_leftMaster.getSelectedSensorPosition()+_leftFollow.getSelectedSensorPosition())/2+(_rightMaster.getSelectedSensorPosition() + _rightFollow.getSelectedSensorPosition())/2)/2)/2048;    
    double error = setPoint - sensorPos;
    double iLimit = 1;
    double dT = Timer.getFPGATimestamp() - lastTimestamp;

    double errorRate = (error - lastError)/dT;
    double motorPower = kP * error + kI * errorSum + kD * errorRate;

    if(Math.abs(error) < iLimit){
      errorSum += error * dT;

    }
    _drive.arcadeDrive(motorPower,motorPower);

    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;
  }
  */
}

