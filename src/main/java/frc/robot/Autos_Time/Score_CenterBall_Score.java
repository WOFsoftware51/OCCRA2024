// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public final class Score_CenterBall_Score extends SequentialCommandGroup {

  /** Example static factory for an autonomous command. */
  public Score_CenterBall_Score(DriveTrain driveTrain, Shooter shooter, Intake intake, Auton_Subsystem auton_Subsystem, boolean isLeft){

    addCommands(
      new Score(shooter, intake, auton_Subsystem),
      new Middle_CenterBall(driveTrain, isLeft).raceWith(Commands.run(()-> auton_Subsystem.autonIntake(intake))),
      new Score(shooter, intake, auton_Subsystem)
    );
  } 
}
