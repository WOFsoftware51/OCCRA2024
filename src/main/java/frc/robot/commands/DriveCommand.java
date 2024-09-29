// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Global_Variables;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  private final DriveTrain m_DriveTrain;
  private double m_Speed;
  private double m_RotationRate;

  public DriveCommand(DriveTrain driveTrain, DoubleSupplier speedSupplier, DoubleSupplier rotationRateSupplier) {
    this.m_DriveTrain = driveTrain;
    addRequirements(driveTrain);

    this.m_Speed = speedSupplier.getAsDouble();
    this.m_RotationRate = rotationRateSupplier.getAsDouble();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(Global_Variables.boostOn)
    {
      Global_Variables.drivePercentModifier = 1.0;
    }
    else{
      Global_Variables.drivePercentModifier = Constants.DriveTrain.DEFAULT_SPEED;
    }


    m_DriveTrain.drive(m_Speed * Global_Variables.drivePercentModifier, m_RotationRate * Global_Variables.rotationPercentModifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
   {
    return false;
  }
}
