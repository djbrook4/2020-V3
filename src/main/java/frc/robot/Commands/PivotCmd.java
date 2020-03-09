/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;


/**
 * An example command.  You can replace me with your own command.
 */
public class PivotCmd extends Command {
  //public static OI m_oi;
  private double speed=0;
  public PivotCmd(double speedInput) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.pivotSub);
    speed = speedInput;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.pivotSub.initDefaultCommand();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("pivot command");
    

  Robot.pivotSub.update(speed);

        
        
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
    Robot.pivotSub.update(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run


  //implement PID loop here


  @Override
  protected void interrupted() {
    end();
  }
}
