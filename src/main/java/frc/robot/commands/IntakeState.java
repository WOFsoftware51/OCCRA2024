// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Global_Variables;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.State;

public class IntakeState extends Command {
  private Intake m_Intake;
  private State m_state = Intake.State.IN; 
  
  /** Creates a new IntakeState. */
  public IntakeState(Intake intake, Intake.State state) 
  {
    m_Intake = intake;
    addRequirements(m_Intake);

    this.m_state = state;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(m_state == Intake.State.IN)
    {
      if(Global_Variables.getSensorVal() == 1 && Global_Variables.isShooting == false)
      {
        m_Intake.floorIntakeOff();
        m_Intake.transferIntakeOff();
      }
      else
      {
        m_Intake.floorIntakeIn();
        m_Intake.transferIntakeIn();
      }
    }
    else
    {
      m_Intake.floorIntakeOut();
      m_Intake.transferIntakeOut();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_Intake.floorIntakeOff();
    m_Intake.transferIntakeOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
