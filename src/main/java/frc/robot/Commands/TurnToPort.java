/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;
import frc.robot.OI;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.utils.Limelight;

/**
 * An example command.  You can replace me with your own command.
 */
public class TurnToPort extends Command {
  public static OI m_oi;
  public static Boolean reverse;
  public TurnToPort() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSub.initDefaultCommand();
  }


  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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

    double forward = 0;
    double turn = 0;
  
    turn = RobotMap.kTurnP*(x/10);
        
    

    Robot.driveSub.arcadeDrive(forward, turn, false);
   // if(reverse == false){
     // Robot.driveSub.arcadeDrive(forward, turn);
    //}
    //if(reverse == true){
     // Robot.driveSub.arcadeDrive(-forward, -turn);
    //}
        
  //System.out.println("command call");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
  
}
