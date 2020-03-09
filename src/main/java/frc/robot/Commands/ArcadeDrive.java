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


/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDrive extends Command {
  public static OI m_oi;
  public static Boolean reverse;
  public ArcadeDrive() {
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
    //System.out.println("aracde command");
    double forward = 1 * Robot.m_oi._driver.getY();
    double turn = Robot.m_oi._driver.getTwist();
    boolean driveslow = Robot.m_oi._driver.getRawButton(1);
    boolean driveReverse = Robot.m_oi._driver.getRawButton(2);

    /*if(Robot.m_oi._driver.getRawButton(11) && reverse == false){
      reverse = true;
    }
    else if(Robot.m_oi._driver.getRawButton(11) && reverse == true){
      reverse = false;
    }*/
    
    if (driveslow) {
      //slow down inputs for better control
      if(forward > .45) forward=.45;
      if(forward < -.45) forward=-.45;
      if(turn > .45) turn=.35;
      if(turn < -.45) turn=-.35;

    } else  {
      //leave inputs be and don't adjust
     // if(turn > .45) turn=turn/2;
     // if(turn < -.45) turn=-(turn/2);

    }

    if (driveReverse) {
      forward = forward * -1;
      turn = turn * 1;
    }
    

    Robot.driveSub.arcadeDrive(forward*.90, turn*.55, false);
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
