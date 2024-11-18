// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global_Variables;
import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends SubsystemBase {
  // private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
  private double angle = 0;
  private AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  private final WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT_ID);
  private final WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT_ID);
  private final WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(Constants.DriveTrain.BACK_LEFT_ID);
  private final WPI_TalonSRX backRightDrive = new WPI_TalonSRX(Constants.DriveTrain.BACK_RIGHT_ID);
  private DifferentialDrive m_robotDrive;

  public DriveTrain() 
  {
    m_gyro.reset();

    backLeftDrive.follow(frontLeftDrive);
    backRightDrive.follow(frontRightDrive);
    frontLeftDrive.setInverted(true);
    backLeftDrive.setInverted(true);
    frontRightDrive.setInverted(false);
    backRightDrive.setInverted(false);

    frontLeftDrive.setNeutralMode(NeutralMode.Brake);
    backLeftDrive.setNeutralMode(NeutralMode.Brake);
    frontRightDrive.setNeutralMode(NeutralMode.Brake);
    backRightDrive.setNeutralMode(NeutralMode.Brake);

    m_robotDrive = new DifferentialDrive(frontLeftDrive, frontRightDrive);
    m_robotDrive.setSafetyEnabled(false);
  } 

  public void drive(double xSpeed, double zRotationRate) 
  {
    double modifiedSpeed = Math.pow(xSpeed,3);
    double modifiedRotationRate = Math.pow(zRotationRate,3);

    m_robotDrive.curvatureDrive(modifiedSpeed * Global_Variables.drivePercentModifier, modifiedRotationRate * Global_Variables.rotationPercentModifier, true);
  }
  
  public void driveAuton(double xSpeed, double zRotationRate) 
  {
    m_robotDrive.curvatureDrive(-xSpeed, zRotationRate, true);
  }

  // public double getLeftDriveTrainEncoder()
  // {
  //   return frontLeftDrive.getSelectedSensorPosition()*360*(1/4096)*Constants.DriveTrain.DRIVE_GEARRATIO;
  // }

  // public double getRightDriveTrainEncoder()
  // {
  //   return frontRightDrive.getSelectedSensorPosition()*360*(1/4096)*Constants.DriveTrain.DRIVE_GEARRATIO;
  // }
  double kP = 0.01625;
  public double powerGoToAngle(double angle)
  {

    if(Math.abs(getGryoDegrees() - angle) <= 10)
    {
      kP = 0.0525;//0.0325; //0.75
    }
    else{
      kP = 0.015;
    }



    return -(getGryoDegrees() - angle) * kP;
  }

  public double getGryoDegrees(){
    return angle;
  }

  public void resetGryo()
  {
    m_gyro.reset();
  }

  public Command gotoAngle(double angle)
  {
    return Commands.run(()-> driveAuton(0, powerGoToAngle(angle)));
  }

  public boolean gyroIsConnected()
  {
    return m_gyro.isConnected();
  }
  @Override
  public void periodic() 
  {
    SmartDashboard.putNumber("Gryo", angle);

    if(gyroIsConnected() && Global_Variables.isUsingGyro)
    {
      angle = m_gyro.getYaw()%360;
    }
    else{
      angle = 0;
    }


  }
}