// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.subsystems.DriveTrain;

public final class LeftBall_Left extends SequentialCommandGroup {

  /** Example static factory for an autonomous command. */
  public LeftBall_Left(DriveTrain driveTrain, boolean isLeft){
    int leftOrRight = isLeft ? 1 : -1;

    addCommands(
      new Auton_DriveCommand_Time(driveTrain, true, -45.0, Constants.AutonPositions.RotationCoordinate.START_TO_CLOSEBALL),
      new Auton_DriveCommand_Time(driveTrain, true, 0 * leftOrRight, 0),
      new Auton_DriveCommand_Time(driveTrain, true, 0, Constants.AutonPositions.XCoordinate.START_TO_CLOSEBALL)
    );
  }
}
