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
public class IntakeCmd extends Command {
  public static OI m_oi;
  private double speed;
  public IntakeCmd(double speedInput) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeSub);
    speed = speedInput;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.intakeSub.initDefaultCommand();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("aracde command");
    

  Robot.intakeSub.update(speed);

        // System.out.println("command call");
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intakeSub.update(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run


  //implement PID loop here

  protected void pid() {
    double p = 0;
    double i = 0;
    double d = 0;
    double iz = 0;
    double ff = 0;
    double max = 0;
    double min = 0;
    double rotations = 0;

  }

  @Override
  protected void interrupted() {
    end();
  }
}
