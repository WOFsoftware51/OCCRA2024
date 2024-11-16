// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Auton_Commands;

import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Auton_Rotate_Command extends Command {
  private final DriveTrain m_DriveTrain;
  private double m_RotationTargetDegrees;
  private boolean endCondition = false;
  private boolean angleGood = false;

  public Auton_Rotate_Command(DriveTrain driveTrain, double rotationTargetDegrees) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_RotationTargetDegrees = rotationTargetDegrees;
  }

  @Override 
  public void initialize()
  {
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    angleGood = (m_DriveTrain.getGryoDegrees() < m_RotationTargetDegrees + 2.5 && m_DriveTrain.getGryoDegrees() > m_RotationTargetDegrees - 2.5);

    if(angleGood)
    {
      m_DriveTrain.driveAuton(0, 0);
      endCondition = true;
    }
    else
    {
      m_DriveTrain.driveAuton(0 , m_DriveTrain.powerGoToAngle(m_RotationTargetDegrees));
    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
      m_DriveTrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
   {
    return endCondition;
  }
}
