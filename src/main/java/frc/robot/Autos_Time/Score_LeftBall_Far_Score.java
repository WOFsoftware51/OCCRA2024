// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;
import frc.robot.Auton_Commands.Auton_Wait;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public final class Score_LeftBall_Far_Score extends SequentialCommandGroup {

  /**Robot MUST be placed perpindicular to the wall. */
  public Score_LeftBall_Far_Score(Shooter shooter, Intake intake, DriveTrain driveTrain, Auton_Subsystem auton_Subsystem, boolean isLeft){
    addCommands(
      new InstantCommand(()-> driveTrain.resetGryo()),
      auton_Subsystem.autonScoreBumperPerpinduclar(intake, shooter),
      new ParallelDeadlineGroup(
        new Left_LeftBall(driveTrain, isLeft),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new LeftBall_Left(driveTrain, isLeft),
      auton_Subsystem.autonScoreBumperAngled(intake, shooter),
      new ParallelDeadlineGroup(
        new Left_FarLeftBall(driveTrain, isLeft),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new FarLeftBall_Left(driveTrain, isLeft),
      auton_Subsystem.autonScoreBumperAngled(intake, shooter)

    );
  }
}
