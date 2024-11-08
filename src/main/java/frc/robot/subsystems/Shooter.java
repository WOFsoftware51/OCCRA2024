// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global_Variables;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX motor1 = new WPI_TalonSRX(Constants.Shooter.SHOOTER_1);
  private WPI_TalonSRX motor2 = new WPI_TalonSRX (Constants.Shooter.SHOOTER_2);
  private final double kFHumanPlayer = 0.04;
  private final double kFBasket = 0.05;
  private double targetVelocity = 0;

  public Shooter(){
    // motor1.set/   
    motor1.setSelectedSensorPosition(0.0);
    motor2.setSelectedSensorPosition(0.0);
    motor1.setNeutralMode(NeutralMode.Coast);
    motor1.setInverted(true);
    motor2.setNeutralMode(NeutralMode.Coast);
    motor2.setInverted(true);
    // motor2.follow(motor1);

    motor1.config_kF( 0, kFBasket);
    motor1.config_kP(0, 0.1);
    motor1.config_kI(0, 0.0);
    motor1.config_kD(0, 0);

    motor2.config_kF( 0, 0.03);
    motor2.config_kP(0, 0.1);
    motor2.config_kI(0, 0.0);
    motor2.config_kD(0, 0);
  }

  public double getTargetVelocity()
  {
    return targetVelocity;
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
    motor1.set(0.75); //4000 RPM
    // motor2.set(0.75);
  }

  public void setOnBasketVelocityRPM()
  {
    targetVelocity = Global_Variables.velocitySendableChooser.getSelected();

    motor1.config_kF(0, kFBasket);
    motor1.set(TalonSRXControlMode.Velocity, targetVelocity*(4096.0/600.0));

    motor2.config_kF(0, 0.04);
    motor2.set(TalonSRXControlMode.Velocity, targetVelocity*(4096.0/600.0));
  }
  public void setOnHumanPlayerVelocityRPM()
  {
    targetVelocity = 2750.0;
    motor1.config_kF(0, kFHumanPlayer);
    motor1.set(TalonSRXControlMode.Velocity, targetVelocity*(4096.0/600.0));

    motor2.config_kF(0, 0.03);
    motor2.set(TalonSRXControlMode.Velocity, targetVelocity*(4096.0/600.0));

  }


  public void setOff()
  {
    motor1.set(0.0);
    motor2.set(0.0);
  }

  public double getVelocity1RPM()
  {
    return motor1.getSelectedSensorVelocity()*600/4096;
  }

  public double getVelocity2RPM()
  {
    return motor2.getSelectedSensorVelocity()*600/4096;
  }



  public double getPositionRotations()
  {
    return motor1.getSelectedSensorPosition()/4096;
  }


  // public double getGotoVeloctiy(double targetVelocity)
  // {
  //   PIDController pidController = new PIDController(0.1, 0, 0);
    
  //   return pidController.calculate(targetVelocity);
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Is Shooting", Global_Variables.isShooting);
    SmartDashboard.putNumber("Shooter 1 RPM", getVelocity1RPM());
    SmartDashboard.putNumber("Shooter 2 RPM", getVelocity2RPM());

    SmartDashboard.putNumber("Shooter Rotations", getPositionRotations());
    SmartDashboard.putString("Scoring Mode", Global_Variables.currentScoringMode.toString());
    SmartDashboard.putNumber("Shooter Target Velocity", targetVelocity);
    // SmartDashboard.putNumber("Scoring testvariable", testVariable);
  }
}
