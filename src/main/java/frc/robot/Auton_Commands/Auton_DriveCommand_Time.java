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
  private double m_speed;
  private boolean m_useGyro = false;

  public Auton_DriveCommand_Time(DriveTrain driveTrain, boolean isNegative, double rotationTargetDegrees, double durationSeconds) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_isNegative = isNegative;
    m_RotationTargetDegrees = rotationTargetDegrees;
    m_durationSeconds = durationSeconds;
    m_speed = 0.75;
    m_useGyro = true;
  }

  public Auton_DriveCommand_Time(DriveTrain driveTrain, boolean isNegative, double rotationTargetDegrees, double durationSeconds, double speed, boolean useGyro) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_isNegative = isNegative;
    m_durationSeconds = durationSeconds;
    m_RotationTargetDegrees = rotationTargetDegrees;
    m_speed = speed;
    m_useGyro = useGyro;
  }

  public Auton_DriveCommand_Time(DriveTrain driveTrain, boolean isNegative, double durationSeconds, double speed, boolean useGyro) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_isNegative = isNegative;
    m_durationSeconds = durationSeconds;
    m_speed = speed;
    m_useGyro = useGyro;
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
    if(m_useGyro)
    {
      angleGood = !m_DriveTrain.gyroIsConnected() || (m_DriveTrain.getGryoDegrees() < m_RotationTargetDegrees + 4 && m_DriveTrain.getGryoDegrees() > m_RotationTargetDegrees - 4);

      if(m_durationSeconds < counter*0.05 && angleGood)
      {
        m_DriveTrain.driveAuton(0, 0);
        endCondition = true;
      }
      else
      {
        counter++;
        m_DriveTrain.driveAuton(m_Negative * m_speed, m_DriveTrain.powerGoToAngle(m_RotationTargetDegrees));
      }
    }
    else
    {
      if(m_durationSeconds < counter*0.05)
      {
        m_DriveTrain.driveAuton(0, 0);
        endCondition = true;
      }
      else
      {
        counter++;
        m_DriveTrain.driveAuton(m_Negative * m_speed, 0);
      }
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
