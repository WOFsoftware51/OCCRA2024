// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Auton_Commands;

import frc.robot.Constants;
import frc.robot.Global_Variables;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Auton_DriveCommand_Time extends Command {
  private final DriveTrain m_DriveTrain;
  private double m_Speed;
  private double m_RotationTargetDegrees;
  private double m_durationSeconds;
  private double counter = 0;
  private boolean endCondition = false;

  public Auton_DriveCommand_Time(DriveTrain driveTrain, double speed, double rotationTargetDegrees, double durationSeconds) {
    m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    m_Speed = speed;
    m_RotationTargetDegrees = rotationTargetDegrees;
    m_durationSeconds = durationSeconds;
  }

  @Override 
  public void initialize()
  {
    counter = 0;
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(m_durationSeconds > counter*0.05 )
    {
      counter++;
      m_DriveTrain.driveAuton(m_Speed, m_DriveTrain.powerGoToAngle(m_RotationTargetDegrees));
    }
    else
    {
      m_DriveTrain.driveAuton(0, 0);
      endCondition = true;
    }


    // if(m_durationSeconds < counter*0.05 )
    // {
    //   m_DriveTrain.driveAuton(0, 0);
    //   endCondition = true;
    // }
    // else
    // {
    //   counter++;
    //   m_DriveTrain.driveAuton(m_Speed, m_DriveTrain.powerGoToAngle(m_RotationTargetDegrees));
    // }


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
