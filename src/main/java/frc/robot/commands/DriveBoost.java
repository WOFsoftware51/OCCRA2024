// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Global_Variables;

public class DriveBoost extends Command {  
  /**Default Speed: {@value #defaultSpeed}  <p> Boost Speed: 1.0 */
  public DriveBoost() 
  {
  }

  @Override
  public void initialize() 
  {
    Global_Variables.boostOn = true;
  }
  @Override
  public void end(boolean interrupted) 
  {
    Global_Variables.boostOn = false;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
