// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.Auton_Commands.Auton_Rotate_Command;
import frc.robot.Constants.AutonPositions;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public final class Score_Leave_Get2CloseBalls extends SequentialCommandGroup {

  /**Robot must be placed facing a ball. 
   * <p>Robot scores, leaves, picks up ball, drives back to starting position, then scores.*/
  public Score_Leave_Get2CloseBalls(Shooter shooter, Intake intake, DriveTrain driveTrain, Auton_Subsystem auton_Subsystem, boolean isLeft){
    int leftOrRight = isLeft ? -1 : 1;
    addCommands(
      new InstantCommand(()-> driveTrain.resetGryo()),
      auton_Subsystem.autonScoreBumperAngled(intake, shooter),
      new ParallelDeadlineGroup(
        new Auton_DriveCommand_Time(driveTrain, false, 0, (AutonPositions.FEET_TO_SECONDS(Math.sqrt(168)))),     //Constants.AutonPositions.XCoordinate.START_TO_WHITELINE),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new ParallelDeadlineGroup(
          new Auton_DriveCommand_Time(driveTrain, true, 0, (AutonPositions.FEET_TO_SECONDS(Math.sqrt(168)))),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new Score(shooter, intake, auton_Subsystem),
      new Auton_Rotate_Command(driveTrain, 54.0 * leftOrRight),
      new ParallelDeadlineGroup(
        new Auton_DriveCommand_Time(driveTrain, false, 54.0 * leftOrRight, (AutonPositions.FEET_TO_SECONDS(Math.sqrt(170) + 2))),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
        new ParallelDeadlineGroup(
          new Auton_DriveCommand_Time(driveTrain, true, 54.0, (AutonPositions.FEET_TO_SECONDS(Math.sqrt(170)))),
          auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new Score(shooter, intake, auton_Subsystem)
    );
  }
}
