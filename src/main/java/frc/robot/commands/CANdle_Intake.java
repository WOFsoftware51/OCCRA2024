// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Global_Variables;
import frc.robot.subsystems.CANdle_Subsystem;

public class CANdle_Intake extends Command {
  private CANdle_Subsystem m_CANdle;
  /** Creates a new CANdle_Intake. */
  public CANdle_Intake(CANdle_Subsystem CANdle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_CANdle = CANdle;
    addRequirements(CANdle);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(Global_Variables.getSensorVal() == 1)
    {
      m_CANdle.CANdle_Orange();
    }
    else if(Global_Variables.boostOn)
    {
      m_CANdle.CANdle_Purple_Larson();
    }
    else
    {
      m_CANdle.CANdle_Purple();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
