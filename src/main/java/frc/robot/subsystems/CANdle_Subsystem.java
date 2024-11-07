// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.FireAnimation;
import com.ctre.phoenix.led.LarsonAnimation;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.StrobeAnimation;



public class CANdle_Subsystem extends SubsystemBase 
{

   /** Creates a new CANdle. */
  private final CANdle m_candle = new CANdle(0);
  private final int LedCount = 90;
  private Animation m_toAnimate;

  public CANdle_Subsystem() 
  {  
    CANdleConfiguration configAll = new CANdleConfiguration();
    configAll.statusLedOffWhenActive = true;
    configAll.disableWhenLOS = false;
    configAll.stripType = LEDStripType.RGB;
    configAll.vBatOutputMode = VBatOutputMode.Modulated;
    m_candle.configAllSettings(configAll, 100);
  }
 
  public void CANdle_off() 
  {
    m_toAnimate = new StrobeAnimation(0, 0, 0, 0, 0, LedCount, 8);
  }
  
  public void CANdle_Animate() 
  {
    m_candle.animate(m_toAnimate);
  }
  /** Flashing Purple*/
  public void CANdle_Purple() 
  {  
    m_toAnimate = new StrobeAnimation(100, 10, 100, 0, 0.3, LedCount, 8);
  }

  public void CANdle_Fast_Purple() 
  {  
    m_toAnimate = new StrobeAnimation(100, 10, 100, 0, 0.1, LedCount, 8);
  }

  public void CANdle_Orange() 
  {  
    m_toAnimate = new StrobeAnimation(255, 40 , 0, 0, 1, LedCount, 8);
  }

  /** Purple But doesm;t flash*/
  public void CANdle_Default() 
  {  
    m_toAnimate = new StrobeAnimation(100, 10, 100, 0, 1.0, LedCount, 8);
    // m_toAnimate = new RainbowAnimation(1.0, 0.1, LedCount, false, 0);
  }

  public void CANdle_Rainbow() 
  {  
    m_toAnimate = new RainbowAnimation(1.0, 0.0, LedCount, false, 8);
  }
  public void CANdle_Fast_Rainbow() 
  {  
    m_toAnimate = new RainbowAnimation(1.0, 0.1, LedCount, false, 8);
  }


  public void CANdle_Solid_Green() 
  {  
    m_toAnimate = new StrobeAnimation(10, 255, 10, 0, 1.0, LedCount, 8);
  }
  public void CANdle_Red() 
  {  
    m_toAnimate = new StrobeAnimation(100, 0, 0, 0, 0.5, LedCount, 1);
  }

  public void CANdle_Blue() 
  {  
    m_toAnimate = new StrobeAnimation(0, 0, 100, 0, 1, LedCount, 6);
  }

  public void CANdle_Purple_Larson() 
  {  
    m_toAnimate = new LarsonAnimation(100, 10, 100, 0, 1.0, LedCount, BounceMode.Front, 7, 8);
  }

  public void CANdle_Fire_Animation() 
  {  
    m_toAnimate = new FireAnimation(1, 0.5, LedCount, 0.75, 0.1, true, 8);
  }
  public void CANdle_Solid_White() 
  {  
    m_toAnimate = new StrobeAnimation(200, 200, 200, 100, 0.5, LedCount, 8);
  }

  
  public Command CANdle_Command(Runnable animationToRun)
  {
    return Commands.run(animationToRun, this);
  }

  public Command CANdle_Orange_Commad()
  {
    return CANdle_Command(()-> CANdle_Orange()).finallyDo(()-> CANdle_off());
  }

  public Command CANdle_Default_Commad()
  {
    return CANdle_Command(()-> CANdle_Default()).ignoringDisable(true);
  }   
  public Command CANdle_Purple_Larson_Commad()
  {
    return CANdle_Command(()-> CANdle_Fast_Purple());
  }


  @Override
  public void periodic() 
  {  
    // if(count > 0){
    //   m_candle.clearAnimation(0);
    //   count++;
    // }
    m_candle.animate(m_toAnimate);
  }
}

