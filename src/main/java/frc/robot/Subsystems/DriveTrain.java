/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Commands.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public WPI_TalonFX _leftMaster = new WPI_TalonFX(RobotMap.DRIVETRAIN_LEFT_FRONT);
    public WPI_TalonFX _rightMaster = new WPI_TalonFX(RobotMap.DRIVETRAIN_RIGHT_FRONT);
    public WPI_TalonFX  _leftFollow = new WPI_TalonFX (RobotMap.DRIVETRAIN_LEFT_BACK);
    public WPI_TalonFX  _rightFollow = new WPI_TalonFX (RobotMap.DRIVETRAIN_RIGHT_BACK);
    public DifferentialDrive _drive = new DifferentialDrive(_leftMaster, _rightMaster);
    public static OI m_oi;

    public DriveTrain(){
      // Set the default command for a subsystem here.
      _leftMaster.configFactoryDefault();
      _rightMaster.configFactoryDefault();
      _leftFollow.configFactoryDefault();
      _rightFollow.configFactoryDefault();

      _leftMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
      _rightMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
      
      _leftFollow.follow(_leftMaster);
      _rightFollow.follow(_rightMaster);
      
      _leftMaster.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
      _rightMaster.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward
      _leftFollow.setInverted(InvertType.FollowMaster);
      _rightFollow.setInverted(InvertType.FollowMaster);
      _drive.setRightSideInverted(false); // do not change this
     //setDefaultCommand(new liftAxis());
     System.out.print("init drivetrain");
    }

    @Override
    public void initDefaultCommand() {
     // System.out.println("default command");      

       setDefaultCommand(new ArcadeDrive());
    }
    public void drive(double leftPower, double rightPower) {
      _leftMaster.set(ControlMode.PercentOutput, leftPower);
      _leftFollow.set(ControlMode.PercentOutput, leftPower);
      _rightMaster.set(ControlMode.PercentOutput, rightPower);
      _rightFollow.set(ControlMode.PercentOutput, rightPower);
  }

  public void stopDrive() {
      _leftMaster.set(ControlMode.PercentOutput, 0);
      _leftFollow.set(ControlMode.PercentOutput, 0);
      _rightMaster.set(ControlMode.PercentOutput, 0);
      _rightFollow.set(ControlMode.PercentOutput, 0);
  }

  public double getLeftEncoder() {
      return (_leftMaster.getSelectedSensorPosition() * RobotMap.DISTANCE_PER_PULSE);
  }

  public double getRightEncoder() {
      return (_rightMaster.getSelectedSensorPosition() * RobotMap.DISTANCE_PER_PULSE);
  }

  public double getAverageEncoder() {
      return (getLeftEncoder() + getRightEncoder()) / 2;
  }

  public void resetEncoder() {
      _leftMaster.setSelectedSensorPosition(0);
      _rightMaster.setSelectedSensorPosition(0);
  }

  public void driveWithMinPower(double leftPower, double rightPower, double minAbsolutePower) {
      double realLeftPower = (leftPower / Math.abs(leftPower)) * Math.max(Math.abs(leftPower), minAbsolutePower);
      double realRightPower = (rightPower / Math.abs(rightPower)) * Math.max(Math.abs(rightPower), minAbsolutePower);
  }


      public void arcadeDrive(double speed, double turn, Boolean flip){
        //System.out.println(speed);

        _drive.arcadeDrive(-speed, turn);
      }
      @Override
      public void periodic() {
  
          // System.out.println("drivetrain periodic");
  
          NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
          NetworkTableEntry tx = table.getEntry("tx");
          NetworkTableEntry ty = table.getEntry("ty");
          NetworkTableEntry ta = table.getEntry("ta");
  
  //read values periodically
          double x = tx.getDouble(0.0);
          double y = ty.getDouble(0.0);
          double area = ta.getDouble(0.0);
  
  //post to smart dashboard periodically
          SmartDashboard.putNumber("LimelightX", x);
          SmartDashboard.putNumber("LimelightY", y);
          SmartDashboard.putNumber("LimelightArea", area);
      }
  }