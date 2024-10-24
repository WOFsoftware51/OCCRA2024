// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global_Variables;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX motor1 = new WPI_TalonSRX(Constants.Shooter.SHOOTER_1);
  private WPI_TalonSRX motor2 = new WPI_TalonSRX(Constants.Shooter.SHOOTER_2);

  public Shooter(){
    motor1.setNeutralMode(NeutralMode.Coast);
    motor1.setInverted(true);
    motor2.setNeutralMode(NeutralMode.Coast);
    motor2.setInverted(true);
  }

  public void motor1OnPercent(double x)
  {
    motor1.set(x);
  }
  public void motor2OnPercent(double x)
  {
    motor2.set(x);
  }
  /**Turns both shooter motors at x speed. <p> 
  */
  public void setPercent(double x)
  {
    motor1OnPercent(x);
    motor2OnPercent(x);
  }

  public void setOn()
  {
    motor1.set(0.75);
    motor2.set(0.75);
  }

  public void setOff()
  {
    motor1.set(0.0);
    motor2.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Is Shooting", Global_Variables.isShooting);
  }
}
