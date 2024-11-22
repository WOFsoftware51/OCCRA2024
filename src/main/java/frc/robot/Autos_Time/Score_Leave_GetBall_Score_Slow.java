// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.Constants.AutonPositions;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public final class Score_Leave_GetBall_Score_Slow extends SequentialCommandGroup {

  /**Robot must be placed facing a ball. 
   * <p>Robot scores, leaves, picks up ball, drives back to starting position, then scores.*/
  public Score_Leave_GetBall_Score_Slow(Shooter shooter, Intake intake, DriveTrain driveTrain, Auton_Subsystem auton_Subsystem){
    addCommands(
      new InstantCommand(()-> driveTrain.resetGryo()),
      auton_Subsystem.autonScoreBumperAngled(intake, shooter),
      new ParallelCommandGroup(
        new Auton_DriveCommand_Time(driveTrain, false,  1.5 *(AutonPositions.FEET_TO_SECONDS(Math.sqrt(168) + 2)),0.375),     //Constants.AutonPositions.XCoordinate.START_TO_WHITELINE),
        auton_Subsystem.autonIntakeUntilHasBall(intake)),
      new Auton_DriveCommand_Time(driveTrain, true,  1.5 *(AutonPositions.FEET_TO_SECONDS(Math.sqrt(168) + 2)),0.375),
      new Score(shooter, intake, auton_Subsystem)
    );
  }
}
