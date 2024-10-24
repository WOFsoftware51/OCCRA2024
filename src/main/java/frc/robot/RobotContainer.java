// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Autos_Time.DoNothing;
import frc.robot.Autos_Time.LeaveOnly;
import frc.robot.Autos_Time.Score;
import frc.robot.Autos_Time.Score_FarLeftBall_Score;
import frc.robot.Autos_Time.Score_LeftBall;
import frc.robot.Autos_Time.Score_LeftBall_Score;
import frc.robot.commands.DriveBoost;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeState;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final Intake m_Intake = new Intake();
  private final Shooter m_Shooter = new Shooter();
  private final Hood m_hood = new Hood();
  private final Auton_Subsystem m_Auton_Subsystem = new Auton_Subsystem();

  private SendableChooser<Integer> autonChooser = new SendableChooser<>();
  private final CommandXboxController driver = new CommandXboxController(0);
  private final CommandXboxController operator = new CommandXboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    m_DriveTrain.setDefaultCommand(new DriveCommand(m_DriveTrain, ()-> driver.getLeftY(), ()-> driver.getRightX()));
    configureBindings();
    printToSmartDashboard();
  }

  private void configureBindings() 
  {
    driver.rightTrigger(0.8).whileTrue(new DriveBoost());

    operator.a().whileTrue(new ShooterCommand(m_Shooter));

    operator.rightTrigger(0.8).whileTrue(m_Intake.intakeOutCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //

    operator.rightBumper().whileTrue(m_Intake.intakeInCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //

    /*Human Player Intake*/
    operator.leftBumper().whileTrue(m_Intake.humanPlayerIntakeCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //
    operator.leftBumper().whileTrue(m_hood.setInCommand().finallyDo(()-> m_hood.setOutCommand()));
    
    operator.start().whileTrue(m_hood.setInCommand());
    operator.back().whileTrue(m_hood.setOutCommand());
  }

  private void printToSmartDashboard()
  {
    SmartDashboard.putData("Autons", autonChooser);
    autonChooser.setDefaultOption("Do Nothing", 0);
    autonChooser.addOption("Score", 1);    
    autonChooser.addOption("Score LeftBall Score", 2);
    autonChooser.addOption("Score RightBall Score", 3);
    autonChooser.addOption("Score FarLeftBall Score", 4);
    autonChooser.addOption("Score FarLeftBall Score", 5);
    autonChooser.addOption("Leave Zone", 6);

  }    


  public Command getAutonomousCommand() 
  {
    switch(autonChooser.getSelected())
    {
      case 0: return new DoNothing();
      case 1: return new Score(m_Shooter, m_Intake, m_Auton_Subsystem); 
      case 2: return new Score_LeftBall_Score(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem, true); 
      case 3: return new Score_LeftBall_Score(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem, false); 
      case 4: return new Score_FarLeftBall_Score(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem, true); 
      case 5: return new Score_FarLeftBall_Score(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem, false); 
      case 6: return new LeaveOnly(m_DriveTrain, m_Auton_Subsystem); 

      default: return new DoNothing();
    }    
  }
}