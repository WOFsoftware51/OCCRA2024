// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
  private final DoubleSolenoid m_hanger1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  // private final DoubleSolenoid m_hanger2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, s2, 3);

  private final Compressor m_compressor;

  /** Creates a new Pnematic_Test. */
  public Hood() 
  {
    m_compressor =  new Compressor(PneumaticsModuleType.REVPH);
    m_compressor.enableDigital();
  }

  private void setOut(){
    m_hanger1.set(Value.kReverse);
    // m_hanger2.set(Value.kReverse);
  }

  private void setIn(){
    m_hanger1.set(Value.kForward);
    // m_hanger2.set(Value.kForward);
  }

  public Command setInCommand(){
    return Commands.run(()-> setIn(), this);
  } 

  public Command setOutCommand(){
    return Commands.run(()-> setOut(), this);
  }
 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}