// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Autos_Time.DoNothing;
import frc.robot.Autos_Time.LeaveOnly;
import frc.robot.Autos_Time.Score;
import frc.robot.Autos_Time.Score_FarLeftBall_Score;
import frc.robot.Autos_Time.Score_Leave;
import frc.robot.Autos_Time.Score_Leave_GetBall;
import frc.robot.Autos_Time.Score_Leave_GetBall_Score;
import frc.robot.Autos_Time.Score_LeftBall_Score;
import frc.robot.commands.CANdle_Intake;
import frc.robot.commands.DriveBoost;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShooterGoToVelocity;
import frc.robot.subsystems.Auton_Subsystem;
import frc.robot.subsystems.CANdle_Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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
  private final CANdle_Subsystem m_Candle = new CANdle_Subsystem();

  private SendableChooser<Integer> autonChooser = new SendableChooser<>();
  private SendableChooser<Double> testAutonTimer = new SendableChooser<>();;
  private SendableChooser<Double> m_velocitySendableChooser = new SendableChooser<>();;


  private final CommandXboxController driver = new CommandXboxController(0);
  private final CommandXboxController operator = new CommandXboxController(1);
  private final CommandXboxController testController = new CommandXboxController(2);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    m_DriveTrain.setDefaultCommand(new DriveCommand(m_DriveTrain, ()-> driver.getLeftY(), ()-> driver.getRightX()));
    m_Candle.setDefaultCommand(m_Candle.CANdle_Default_Commad());
    configureBindings();
    printToSmartDashboard();
  }

  private void configureBindings() 
  {

    driver.back().whileTrue(new InstantCommand(()->m_DriveTrain.resetGryo()));

    driver.rightTrigger(0.8).whileTrue(new DriveBoost());
    driver.rightTrigger(0.8).whileTrue(m_Candle.CANdle_Purple_Larson_Commad());

    /**Shooter revs up */
    operator.a().whileTrue(new ShooterGoToVelocity(m_Shooter));

    /*IntakeSpit */
    operator.rightTrigger(0.8).whileTrue(m_Intake.intakeOutCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //

    /*Intake */
    operator.leftTrigger(0.8).whileTrue(m_Intake.BasketIntakeCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //
    operator.leftTrigger(0.8).whileTrue(new CANdle_Intake(m_Candle));

    /*Human Player Intake*/
    operator.leftBumper().whileTrue(m_Intake.humanPlayerIntakeCommand().finallyDo(()-> {m_Intake.floorIntakeOff(); m_Intake.transferIntakeOff();})); //
    operator.leftBumper().whileTrue(m_hood.humanPlayerInCommand().finallyDo(()->m_hood.setOut1()));
      
    operator.back().whileTrue(m_hood.hangOutCommand());
    operator.start().whileTrue(m_hood.hangInCommand());
  }

  private void printToSmartDashboard()
  {
    SmartDashboard.putData("Autons", autonChooser);
    autonChooser.setDefaultOption("Do Nothing", 0);
    autonChooser.addOption("Score", 1);    
    // autonChooser.addOption("Score LeftBall Score", 2);
    // autonChooser.addOption("Score RightBall Score", 3);
    // autonChooser.addOption("Score FarLeftBall Score", 4);
    // autonChooser.addOption("Score FarRightBall Score", 5);
    autonChooser.addOption("Leave Zone Only", 6);
    autonChooser.addOption("Score + Leave Zone", 7);
    autonChooser.addOption("Score + Leave + Get Ball", 8);
    autonChooser.addOption("Score + Get Ball + Score", 9);

    SmartDashboard.putData("Auton Timer", testAutonTimer);

    // testAutonTimer.setDefaultOption("Time: 13.59375",  13.59375);
    // for(double i = 0; i <= 5; i+=0.5)
    // {
    //   testAutonTimer.addOption("Time: " + i, i);
    // }
    Global_Variables.testAutonTimer = testAutonTimer;

    SmartDashboard.putData("Shooter Velocity", m_velocitySendableChooser);
    m_velocitySendableChooser.setDefaultOption("Default Shooter Velocity: 1575.0",  1575.0);
    for(double i = 0; i <= 4; i++)
    {
      m_velocitySendableChooser.addOption("Shooter Velocity: " + ((1575 + (25 * 5)) - (25 * i)), ((1575 + (25 * 5)) - (25 * i)));
    }
    for(double i = 1; i <= 5; i++)
    {
      m_velocitySendableChooser.addOption("Shooter Velocity: " + (1575 - (25 * i)), 1575.0 - (25.0 * i));
    }
    Global_Variables.velocitySendableChooser = m_velocitySendableChooser;
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
      case 7: return new Score_Leave(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem); 
      case 8: return new Score_Leave_GetBall(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem); 
      case 9: return new Score_Leave_GetBall_Score(m_Shooter, m_Intake, m_DriveTrain, m_Auton_Subsystem); 

      default: return new DoNothing();
    }    
  }
}