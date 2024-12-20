// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
  private boolean isSolenoidInitialized = false;
  private final DoubleSolenoid m_hanger1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 2);
  private final DoubleSolenoid m_hanger2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  private final Compressor m_compressor;

  /** Creates a new Pnematic_Test. */
  public Hood() 
  {
    m_compressor =  new Compressor(PneumaticsModuleType.REVPH);
    m_compressor.enableDigital();
  }

  public void setOut1(){
    m_hanger1.set(Value.kReverse);
  }

  private void setIn1(){
    m_hanger1.set(Value.kForward);
    m_hanger2.set(Value.kReverse);
  }

  private void setOut2(){
    m_hanger2.set(Value.kReverse);
  }

  private void setIn2(){
    m_hanger2.set(Value.kForward);
  }

  private void setInBoth(){
    m_hanger1.set(Value.kReverse);
    m_hanger2.set(Value.kForward);
  }

  private void setOutBoth(){
    m_hanger1.set(Value.kForward);
    m_hanger2.set(Value.kReverse);
  }

  public Command humanPlayerInCommand(){
    return Commands.run(()-> setIn1());
  } 
  public Command humanPlayerOutCommand(){
    return Commands.run(()-> setOut1());
  } 

  public Command hangInCommand(){
    return Commands.run(()-> setOutBoth());
  } 

  public Command hangOutCommand(){
    return Commands.run(()-> setInBoth());
  }
 

  @Override
  public void periodic() 
  {
    SmartDashboard.putNumber("Compressor pressure", m_compressor.getPressure());
    SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());

    if(DriverStation.isEnabled() && !isSolenoidInitialized)
    {
      setOut2();
      setOut1();
      isSolenoidInitialized = true;
    }
  }
}
