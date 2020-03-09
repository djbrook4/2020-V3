/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.RobotMap;
import frc.robot.Commands.*;

import edu.wpi.first.wpilibj.Timer;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
      WPI_VictorSPX VictorSPX = new WPI_VictorSPX(RobotMap.ELEVATOR_LEFT);
      WPI_VictorSPX VictorSPX2 = new WPI_VictorSPX(RobotMap.ELEVATOR_RIGHT);
      

      public Elevator(){

      }


    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
       setDefaultCommand(new ElevatorCmd(0));
    }
  
  
      public void update(double motorSpeed) {
        // Update motor speed to passed in value
        VictorSPX.setNeutralMode(NeutralMode.Brake);
        VictorSPX2.setNeutralMode(NeutralMode.Brake);

        VictorSPX.set(-motorSpeed);
        VictorSPX2.set(motorSpeed);
        //System.out.print();
      }
      
      
  }

