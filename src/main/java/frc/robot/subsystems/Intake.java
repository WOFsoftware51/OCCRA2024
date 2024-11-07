// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global_Variables;
import frc.robot.Global_Variables.SCORING_MODE;

public class Intake extends SubsystemBase {
  private WPI_TalonSRX m_floorIntake1 = new WPI_TalonSRX(Constants.Intake.TRANSFER_INTAKE_1);
  private WPI_TalonSRX m_transferIntake1 = new WPI_TalonSRX(Constants.Intake.FLOOR_INTAKE_1);
  private SCORING_MODE currentScoringMode = SCORING_MODE.BASKET;
  /** Creates a new FloorIntake. */
  public Intake()
  {
    m_floorIntake1.setNeutralMode(NeutralMode.Coast);
    m_floorIntake1.setInverted(false);

    m_transferIntake1.setNeutralMode(NeutralMode.Brake);
    m_transferIntake1.setInverted(true);
    
  }

  public void floorIntakeIn()
  {
    m_floorIntake1.set(0.75);
  }
  public void floorIntakeOut()
  {
    m_floorIntake1.set(-0.75);
  }
  public void floorIntakeOut(double percentPower)
  {
    m_floorIntake1.set(percentPower);
  }

  public void floorIntakeOff()
  {
    m_floorIntake1.set(0.0);
  }
  
  public void transferIntakeIn()
  {
    m_transferIntake1.set(0.75);
  }
  public void transferIntakeOut()
  {
    m_transferIntake1.set(-0.75);
  }
  public void transferIntakeOff()
  {
    m_transferIntake1.set(0.0);
  }

  public void bothOff()
  {
    m_transferIntake1.set(0.0);
    m_floorIntake1.set(0.0);
  }

  public Command humanPlayerIntakeCommand()
  {
    return Commands.run(()-> 
    {      
      currentScoringMode = SCORING_MODE.HUMAN_PLAYER;
      if(Global_Variables.getSensorVal() == 1 && !Global_Variables.isShooting)
      {
        transferIntakeOff();
        floorIntakeOff();
      }
      else
      {
        transferIntakeIn();
        floorIntakeOut(-0.5);
      }

    }, this).
    finallyDo(()-> 
    {
      currentScoringMode = SCORING_MODE.BASKET;
      floorIntakeOff(); 
      transferIntakeOff();
    });
  }

  public Command BasketIntakeCommand()
  {
    return Commands.run(()-> 
    {
      currentScoringMode = SCORING_MODE.BASKET;
      if(Global_Variables.getSensorVal() == 1 && !Global_Variables.isShooting)
      {
        transferIntakeOff();
        floorIntakeOff();
      }
      else
      {
        transferIntakeIn();
        floorIntakeIn();
      }
    }, this).
    finallyDo(()->
    {
      floorIntakeOff(); 
      transferIntakeOff();
    });

  }


  public Command intakeOutCommand()
  {
    return Commands.run(()-> 
    {
      transferIntakeOut();
      floorIntakeOut();
    }, this).
    finallyDo(()->
    {
      floorIntakeOff(); 
      transferIntakeOff();
    });
  }
 
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Proximity Sensor", Global_Variables.getSensorVal());
    Global_Variables.currentScoringMode = currentScoringMode;
  }

  public enum State
  {
    IN,
    OUT
  }
}