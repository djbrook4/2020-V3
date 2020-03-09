/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PWMSparkMax;

import frc.robot.RobotMap;
import frc.robot.Commands.*;

import edu.wpi.first.wpilibj.Timer;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pivot extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
      PWMSparkMax PivotSparkMax = new PWMSparkMax(RobotMap.PIVOT);

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
       setDefaultCommand(new PivotCmd(0));
    }
  
  
      public void update(double motorSpeed) {
        // Update motor speed to passed in value
        //System.out.println("pivot");
        System.out.println(motorSpeed);
        PivotSparkMax.setSpeed(motorSpeed);

        //System.out.print();
      }
      
      
  }

