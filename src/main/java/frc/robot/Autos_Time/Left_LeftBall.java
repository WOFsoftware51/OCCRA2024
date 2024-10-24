// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.subsystems.DriveTrain;

public final class Left_LeftBall extends SequentialCommandGroup {

  /** Example static factory for an autonomous command. */
  public Left_LeftBall(DriveTrain driveTrain, boolean isLeft){
    int leftOrRight = isLeft ? 1 : -1;

    addCommands(
      new Auton_DriveCommand_Time(driveTrain, 0.5, 0.0, Constants.AutonPositions.XCoordinate.START_TO_CLOSEBALL),
      new Auton_DriveCommand_Time(driveTrain, 0.0, 45.0 * leftOrRight, Constants.AutonPositions.RotationCoordinate.START_TO_CLOSEBALL),
      new Auton_DriveCommand_Time(driveTrain, 0.5, 0.0, Constants.AutonPositions.YCoordinate.START_TO_CLOSEBALL)
    );
  }
}