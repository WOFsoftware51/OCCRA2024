// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Global_Variables;
import frc.robot.Global_Variables.SCORING_MODE;
import frc.robot.subsystems.Shooter;

public class ShooterGoToVelocity extends Command 
{
  private Shooter m_Shooter;
  /** Creates a new GoToShooterVelocity. */
  public ShooterGoToVelocity(Shooter shooter) 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(Global_Variables.currentScoringMode == SCORING_MODE.BASKET)
    {
        m_Shooter.setOnBasketVelocityRPM(); 
        Global_Variables.isShooting = true;
    }
    else if(Global_Variables.currentScoringMode == SCORING_MODE.HUMAN_PLAYER)
    {
      m_Shooter.setOnHumanPlayerVelocityRPM(); 
      Global_Variables.isShooting = true;
    }
    else
    {
      m_Shooter.setOnFeedingShotVelocityRPM(); 
      Global_Variables.isShooting = true;

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_Shooter.setOff(); 
    Global_Variables.isShooting = false;
  } 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
