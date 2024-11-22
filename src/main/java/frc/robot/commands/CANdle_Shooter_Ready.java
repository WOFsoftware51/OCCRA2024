// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.print.CancelablePrintJob;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Global_Variables;
import frc.robot.subsystems.CANdle_Subsystem;

public class CANdle_Shooter_Ready extends Command {
  private CANdle_Subsystem m_CANdle;
  private boolean isReady;
  public CANdle_Shooter_Ready(CANdle_Subsystem candle) 
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
    if(Global_Variables.shooterCurrentVelocity  + 50 >= Global_Variables.shooterTargetVelocity && Global_Variables.shooterCurrentVelocity - 50 <= Global_Variables.shooterTargetVelocity) 
    {
      isReady = true;
    }
    else
    {
      isReady = false;
    }

    if(isReady)
    {
      m_CANdle.CANdle_Solid_Green();
    }    
    else
    {
      m_CANdle.CANdle_Red();
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
