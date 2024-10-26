// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Auton_Commands;

import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Auton_DriveCommand_Time extends Command {
  private final DriveTrain m_DriveTrain;
  private boolean m_isNegative;
  private double m_RotationTargetDegrees;
  private double m_durationSeconds;
  private double counter = 0;
  private boolean endCondition = false;
  private boolean angleGood = false;
  private double m_Negative;

  public Auton_DriveCommand_Time(DriveTrain driveTrain, boolean isNegative, double rotationTargetDegrees, double durationSeconds) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_isNegative = isNegative;
    m_RotationTargetDegrees = rotationTargetDegrees;
    m_durationSeconds = durationSeconds;
  }

  @Override 
  public void initialize()
  {
    m_Negative = m_isNegative ? -1 : 1;
    counter = 0;
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    angleGood = !m_DriveTrain.gyroIsConnected() || (m_DriveTrain.getGryoDegrees() < m_RotationTargetDegrees + 2.5 && m_DriveTrain.getGryoDegrees() > m_RotationTargetDegrees - 2.5);

    if(m_durationSeconds < counter*0.05 && angleGood)
    {
      m_DriveTrain.driveAuton(0, 0);
      endCondition = true;
    }
    else
    {
      counter++;
      m_DriveTrain.driveAuton(m_Negative * 0.75 , m_DriveTrain.powerGoToAngle(m_RotationTargetDegrees));
    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
      m_DriveTrain.drive(0, 0);
  }

  public double getElapsedSeconds()
  {
    return counter*0.05;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
   {
    return endCondition;
  }
}
