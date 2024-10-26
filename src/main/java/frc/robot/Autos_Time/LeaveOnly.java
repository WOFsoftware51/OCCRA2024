// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos_Time;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Auton_Commands.Auton_DriveCommand_Time;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.DriveTrain;

public final class LeaveOnly extends SequentialCommandGroup {

  /** Example static factory for an autonomous command. */
  public LeaveOnly(DriveTrain driveTrain, Auton_Subsystem auton_Subsystem){
    addCommands(
      new InstantCommand(()-> driveTrain.resetGryo()),
      new Auton_DriveCommand_Time(driveTrain, false, 0.0, Constants.AutonPositions.XCoordinate.START_TO_WHITELINE),
      new Auton_DriveCommand_Time(driveTrain, false, 0.0, 2)
    );
  }
}
