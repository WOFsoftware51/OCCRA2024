// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Global_Variables;
import frc.robot.Global_Variables.SCORING_MODE;
import frc.robot.Auton_Commands.Auton_Wait;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShooterGoToVelocity;

public class Auton_Subsystem extends SubsystemBase 
{

  /** Creates a new Auton_Subsystem. */
  public Auton_Subsystem() {}

  public Command autonScore(Intake intake, Shooter shooter)
  {
    return new SequentialCommandGroup(
      autonShoot(shooter).until(()-> (new Auton_Wait(150.0).getAsBooleanSupplier().getAsBoolean() || shooterUpToSpeed(shooter))),
      new ParallelCommandGroup(
        intake.BasketIntakeCommand().until(()-> !hasBall()),
        new Auton_Wait(50.0)
      ),
      new InstantCommand(()-> {Global_Variables.isShooting = false;})
    );
  }

  public Command autonScoreBumperAngled(Intake intake, Shooter shooter)
  {
    return new SequentialCommandGroup(
      autonShootBumperAngled(shooter).until(()-> (new Auton_Wait(150.0).getAsBooleanSupplier().getAsBoolean() || shooterUpToSpeed(shooter))),
      new ParallelCommandGroup(
        intake.BasketIntakeCommand().until(()-> !hasBall()),
        new Auton_Wait(50.0)
      ),
      new InstantCommand(()-> {Global_Variables.isShooting = false;})
    );
  }

  public Command autonScoreBumperPerpinduclar(Intake intake, Shooter shooter)
  {
    return new SequentialCommandGroup(
      autonShootBumperPerpindicular(shooter).until(()-> (new Auton_Wait(150.0).getAsBooleanSupplier().getAsBoolean() || shooterUpToSpeed(shooter))),
      new ParallelCommandGroup(
        intake.BasketIntakeCommand().until(()-> !hasBall()),
        new Auton_Wait(50.0)
      ),
      new InstantCommand(()-> {Global_Variables.isShooting = false;})
    );
  }

  public Command autonShootWaitUntil(Shooter shooter, double shooterTargetVelocityRPM)
  {
    return Commands.run(()->{}, shooter).until(()-> shooterUpToSpeed(shooter, shooterTargetVelocityRPM));
  }


  private Command autonShoot(Shooter shooter)
  {
    return Commands.run(()-> 
      {
          shooter.setOnBasketVelocityRPM(); 
          Global_Variables.isShooting = true;
      }, shooter);
  }

  /**For Auton */
  private Command autonShootBumperAngled(Shooter shooter)
  {
    return Commands.run(()-> 
      {
          shooter.setOnBumperAngledShotRPM(); 
          Global_Variables.isShooting = true;
      }, shooter);
  }
  /**For Auton */
  private Command autonShootBumperPerpindicular(Shooter shooter)
  {
    return Commands.run(()-> 
      {
          shooter.setOnBumperShotPerpindicularRPM(); 
          Global_Variables.isShooting = true;
      }, shooter);
  }


  public Command autonIntake(Intake intake)
  {
    return intake.BasketIntakeCommand();
  }

  public Command autonIntakeUntilHasBall(Intake intake)
  {
    return intake.BasketIntakeCommand().until(()-> hasBall()).andThen((new InstantCommand(()-> intake.bothOff())));
  }

  /**@returns 
   *       true: if shooter is up to speed
   *  <li> false: if shooter is not up to speed
   */
  public boolean shooterUpToSpeed(Shooter shooter)
  {
    return (shooter.getVelocity1RPM() + 200 >= shooter.getTargetVelocity()) && shooter.getVelocity1RPM() - 200 <= shooter.getTargetVelocity();
  }
    public boolean shooterUpToSpeed(Shooter shooter, double shooterTargetVelocityRPM)
  {
    return (shooter.getVelocity1RPM() + 200 >= shooterTargetVelocityRPM) && shooter.getVelocity1RPM() - 200 <= shooterTargetVelocityRPM;
  }



  private boolean hasBallPlusTime()
  {

    if(hasBall() && new Auton_Wait(1.0).getAsBooleanSupplier().getAsBoolean())
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  private boolean hasBall()
  {
    if(Global_Variables.getSensorVal() == 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public Command autonDrive(DriveTrain driveTrain, double speed, double rotation, double distance)
  {
    return new DriveCommand(driveTrain, ()-> speed, ()-> rotation);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
