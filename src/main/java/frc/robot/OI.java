/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import frc.robot.Commands.*;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Elevator;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick _driver = new Joystick(RobotMap.OI_DRIVER_CONTROLLER);
  public Joystick _operator = new Joystick(RobotMap.OI_OP_CONTROLLER);
  // public XboxController _operator = new
  // XboxController(RobotMap.OI_OP_CONTROLLER);
  public XboxController _squishy = new XboxController(RobotMap.OI_SQUISHY);

  public Button visionButton = new JoystickButton(_driver, 2);
  public Button speedChangeButton = new JoystickButton(_driver, 1);
  public Button reverseDrivetrainButton = new JoystickButton(_driver, 2);
  public Button intakeInDriver = new JoystickButton(_driver, 3);
  public Button intakeOutDriver = new JoystickButton(_driver, 4);

  public Button ShootT = new JoystickButton(_operator, 1);
  public Button ShootL = new JoystickButton(_operator, 2);
  public Button ShootF = new JoystickButton(_operator, 11);
  public Button PivotUp = new JoystickButton(_operator, 5);
  public Button PivotDown = new JoystickButton(_operator, 3);
  public Button ElevatorUp = new JoystickButton(_operator, 6);
  public Button ElevatorDown = new JoystickButton(_operator, 4);

  public Button IntakeIn = new JoystickButton(_operator, 9);
  public Button IntakeOut = new JoystickButton(_operator, 10);

  public Button RunBack = new JoystickButton(_driver, 12);

  public OI() {
    PivotUp.whileHeld(new PivotCmd(.15));
    PivotDown.whileHeld(new PivotCmd(-.15));

    ElevatorDown.whileHeld(new ElevatorCmd(.60)); // down
    ElevatorUp.whileHeld(new ElevatorCmd(-.85)); // up (speed it up)

    ShootL.whileHeld(new ShooterButton(-.20));
    ShootT.whileHeld(new ShooterButton(-.67));
    ShootF.whileHeld(new ShooterButton(-.75));

    IntakeIn.whileHeld(new IndexCmd(.75));
    intakeInDriver.whileHeld(new IntakeCmd(1));

    IntakeOut.whileHeld(new IndexCmd(-.75));
    intakeOutDriver.whileHeld(new IndexCmd(-.75));
    IntakeOut.whileHeld(new ShooterButton(.1));
    intakeOutDriver.whileHeld(new ShooterButton(.1));

    RunBack.whenPressed(new FixedDriveV2());
    // RunBack.whenReleased(new FixedDriveStop());
  

  }
}
