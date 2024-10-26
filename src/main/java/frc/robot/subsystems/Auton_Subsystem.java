// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Global_Variables;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.Auton_Commands.Auton_Wait;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeState;
import frc.robot.commands.ShooterCommand;

public class Auton_Subsystem extends SubsystemBase 
{

  /** Creates a new Auton_Subsystem. */
  public Auton_Subsystem() {}

  public Command autonScore(Intake intake, Shooter shooter)
  {
    return new ParallelRaceGroup(
      new ShooterCommand(shooter).until(new Auton_Wait(3.0).getAsBooleanSupplier()),
      new IntakeState(intake, Intake.State.IN).until(()-> !hasBall())
    );
  }

  public Command autonIntake(Intake intake)
  {
    return new IntakeState(intake, Intake.State.IN);
  }

  public Command autonIntakeUntilHasBall(Intake intake)
  {
    return new IntakeState(intake, Intake.State.IN).until(()-> hasBall());
  }


  private boolean hasBallPlusTime()
  {

    if(hasBall() && new Auton_Wait(11).getAsBooleanSupplier().getAsBoolean())
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  private boolean hasBall()
  {
    if(Global_Variables.getSensorVal() == 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public Command autonDrive(DriveTrain driveTrain, double speed, double rotation, double distance)
  {
    return new DriveCommand(driveTrain, ()-> speed, ()-> rotation);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
