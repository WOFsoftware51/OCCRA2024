// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.Auton_Commands.Auton_Wait;
import frc.robot.Constants.AutonPositions;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public final class Score_Leave_GetBall extends SequentialCommandGroup {

  /**Robot must be placed facing a ball. 
   * <p>Robot scores, leaves, then picks up ball.*/
  public Score_Leave_GetBall(Shooter shooter, Intake intake, DriveTrain driveTrain, Auton_Subsystem auton_Subsystem){
    addCommands(
      new InstantCommand(()-> driveTrain.resetGryo()),
      new Score(shooter, intake, auton_Subsystem),
      new ParallelCommandGroup(
        new Auton_DriveCommand_Time(driveTrain, false, 0.0, AutonPositions.FEET_TO_SECONDS(Math.sqrt(168) + 4)),     //Constants.AutonPositions.XCoordinate.START_TO_WHITELINE),
        auton_Subsystem.autonIntakeUntilHasBall(intake))
    );
  }
}
