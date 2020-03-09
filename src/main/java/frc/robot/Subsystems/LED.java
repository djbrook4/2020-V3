/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.RobotMap;
import frc.robot.Commands.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LED extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;
    // Store what the last hue of the first pixel is
    private int m_rainbowFirstPixelHue=1;      

    public LED(){
      m_led = new AddressableLED(RobotMap.LED_PWM);
      m_ledBuffer = new AddressableLEDBuffer(RobotMap.LED_STRING_LENGTH);
      m_led.setLength(m_ledBuffer.getLength());

      // Set the data
      m_led.setData(m_ledBuffer);
      m_led.start();  
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
       setDefaultCommand(new LEDstartup());
    }
    
    // DRIVER from command
    public void showRainbow() {
      
      rainbow();
      m_led.setData(m_ledBuffer);
    }
    
    // TYPES OF LED FUNCTIONS
    // Turn off strip
    public void empty(){
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 0, 0, 0);
      }
      m_led.setData(m_ledBuffer);
    }

    // Show rainbow
    private void rainbow() {
      // For every pixel
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        // Calculate the hue - hue is easier for rainbows because the color
        // shape is a circle so only one value needs to precess
        final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
        // Set the value
        m_ledBuffer.setHSV(i, hue, 255, 128);
       // System.out.println(i); 
      }
      // Increase by to make the rainbow "move"
      m_rainbowFirstPixelHue += 3;
      // Check bounds
      m_rainbowFirstPixelHue %= 180;
    }
      
    
      
  }

