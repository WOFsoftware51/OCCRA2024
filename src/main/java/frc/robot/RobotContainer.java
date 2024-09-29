// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Autos.DoNothing;
import frc.robot.commands.DriveBoost;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {  
  // Subsystems
  private final DriveTrain m_DriveTrain = new DriveTrain();

  SendableChooser<Integer> autonChooser = new SendableChooser<>();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driver = new CommandXboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    m_DriveTrain.setDefaultCommand(new DriveCommand(m_DriveTrain, ()-> driver.getLeftY(), ()-> driver.getRightX()));
    // Configure the trigger bindings
    configureBindings();
    printToSmartDashboard();
  }

  private void configureBindings() 
  {
    driver.rightTrigger(0.8).onTrue(new DriveBoost());
  }
  private void printToSmartDashboard()
  {
    SmartDashboard.putData("Autons", autonChooser);
    autonChooser.setDefaultOption("Do Nothing", 0);
  }

  public SequentialCommandGroup getAutonomousCommand() 
  {
    // An example command will be run in autonomous
    return new DoNothing();
  }
}
