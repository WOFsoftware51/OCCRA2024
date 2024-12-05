// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.print.CancelablePrintJob;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Global_Variables;
import frc.robot.subsystems.CANdle_Subsystem;

public class CANdle_Christmas extends Command {
  private CANdle_Subsystem m_CANdle;
  private boolean redIsTrue = false;
  private boolean greenIsTrue = false;
  private int redCounter = 0;
  private int greenCounter = 0;

  public CANdle_Christmas(CANdle_Subsystem candle) 
  {
    m_CANdle = candle;
    addRequirements(candle);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(redCounter > 20) 
    {
      redIsTrue = false;
      greenCounter++;
    }
    else
    {
      redIsTrue = true;
    }

    if(greenCounter > 20)
    {
      greenIsTrue = false;
      redCounter++;
    }
    else
    {
      greenIsTrue = true;
    }

    if(redIsTrue)
    {
      m_CANdle.CANdle_Solid_Red();
    }    
    else if(greenIsTrue)
    {
      m_CANdle.CANdle_Solid_Green();
    }
    else
    {
      m_CANdle.CANdle_Solid_Red();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_CANdle.CANdle_Default();

  }
  @Override
  public InterruptionBehavior getInterruptionBehavior(){
    return InterruptionBehavior.kCancelIncoming;
  } 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
