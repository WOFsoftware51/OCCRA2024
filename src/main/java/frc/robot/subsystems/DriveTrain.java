// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global_Variables;

public class DriveTrain extends SubsystemBase {

  private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT_ID);
  private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT_ID);
  private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(Constants.DriveTrain.BACK_LEFT_ID);
  private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(Constants.DriveTrain.BACK_RIGHT_ID);
  private DifferentialDrive m_robotDrive;

  public DriveTrain() 
  {
    frontLeftDrive.setSafetyEnabled(true);
    frontRightDrive.setSafetyEnabled(true);
    backLeftDrive.follow(backLeftDrive);
    backRightDrive.follow(frontRightDrive);
    frontLeftDrive.setInverted(true);
    backLeftDrive.setInverted(true);
    frontRightDrive.setInverted(true);
    backRightDrive.setInverted(true);

    m_robotDrive = new DifferentialDrive(frontLeftDrive, frontRightDrive);
  } 

  public void drive(double speed, double rotationRate) 
  {
    double modifiedSpeed = Math.pow(speed,3);
    double modifiedRotation = Math.pow(rotationRate,3);

    m_robotDrive.curvatureDrive(modifiedSpeed, modifiedRotation, true);
  }

  @Override
  public void periodic() 
  {
  }
}