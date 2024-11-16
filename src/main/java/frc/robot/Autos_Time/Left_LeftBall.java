// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.Auton_Commands.Auton_Rotate_Command;
import frc.robot.subsystems.DriveTrain;

//Coordinates: Ball 1 is (11.348999, 4.374)
public final class Left_LeftBall extends SequentialCommandGroup {

  public Left_LeftBall(DriveTrain driveTrain, boolean isLeft){
    int leftOrRight = isLeft ? 1 : -1;

    addCommands(
      new Auton_DriveCommand_Time(driveTrain, false, 0.0, Constants.AutonPositions.XCoordinate.START_TO_CLOSEBALL),
      new Auton_Rotate_Command(driveTrain, 44.9880608921 * leftOrRight),
      new Auton_DriveCommand_Time(driveTrain, false, 44.9880608921 * leftOrRight, Constants.AutonPositions.XCoordinate.START_TO_CLOSEBALL)
    );
  }
}
