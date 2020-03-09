

package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.RobotMap;
import frc.robot.Commands.*;

import edu.wpi.first.wpilibj.Timer;

public class PivotCan extends Subsystem {
    /**
     * Intake.
     */
    
    CANSparkMax CANSparkMax = new CANSparkMax(12, MotorType.kBrushless);


    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
       setDefaultCommand(new PivotCmd(0));
    }
  
  
      public void update(double motorSpeed) {
        // Update motor speed to passed in value
        //System.out.println("pivot");
       // System.out.println(motorSpeed);
        CANSparkMax.set(motorSpeed);

        //System.out.print();
      }
      
      
  }


