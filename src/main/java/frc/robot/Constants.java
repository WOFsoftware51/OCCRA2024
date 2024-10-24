// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public class DriveTrain
  {
    public static final double DEFAULT_SPEED = 0.6;
    public static final int FRONT_RIGHT_ID = 1; 
    public static final int FRONT_LEFT_ID = 2;
    public static final int BACK_LEFT_ID = 3;
    public static final int BACK_RIGHT_ID = 4;
    // public static final int LEFT_CANCODER_ID = 1;
    // public static final int RIGHT_CANCODER_ID = 2;
    /**Length of the center of the left drive wheel to center of the right drive wheel in inches*/
    public static final double WHEEL_TO_WHEEL_DIAMETER = 22;
    /**In inches */
    public static final double ROTATION_CIRCUMFERANCE = Math.PI * WHEEL_TO_WHEEL_DIAMETER;
    /**Encoder Units to an Angle */
    public static final double ENCODERANGLE_TO_ROBOTANGLE = Math.PI * (WHEEL_TO_WHEEL_DIAMETER*0.5)*(WHEEL_TO_WHEEL_DIAMETER*0.5);
    /** Drive Train gear ratio */
    public static final double DRIVE_GEARRATIO = 8.34;
    
  }
  public class Shooter
  {
    public static final int SHOOTER_1 = 5;
    public static final int SHOOTER_2 = 6;
  }
  public class Intake
  {
    public static final int FLOOR_INTAKE_1 = 7;
    public static final int TRANSFER_INTAKE_1 = 8;
  }

  public class AutonPositions
  {
    private class XCoordinateFeet
    {
      public static double START_TO_CLOSEBALL = 8;
      public static double START_TO_FARBALL = 3.0;
      public static double START_TO_CENTERFARBALL = 3.0;
    }
    private class YCoordinateFeet
    {
      public static double START_TO_CLOSEBALL = 1.0;
      public static double START_TO_FARBALL = 3.0;
    }
    private class RotationCoordinateFeet
    {
      public static double START_TO_CLOSEBALL = 0.5;
      public static double START_TO_FARBALL = 3.0;
    }

    public class XCoordinate
    {
      public static  double START_TO_CLOSEBALL = FEET_TO_SECONDS(XCoordinateFeet.START_TO_CLOSEBALL);
      public static double START_TO_FARBALL = FEET_TO_SECONDS(XCoordinateFeet.START_TO_FARBALL);
      public static double START_TO_CENTERFARBALL = FEET_TO_SECONDS(XCoordinateFeet.START_TO_CENTERFARBALL);
    }
    public class YCoordinate
    {
      public static double START_TO_CLOSEBALL = FEET_TO_SECONDS(YCoordinateFeet.START_TO_CLOSEBALL);
      public static double START_TO_FARBALL = FEET_TO_SECONDS(YCoordinateFeet.START_TO_FARBALL);
    }
    public class RotationCoordinate
    {
      public static double START_TO_CLOSEBALL = FEET_TO_SECONDS(RotationCoordinateFeet.START_TO_CLOSEBALL);
      public static double START_TO_FARBALL = FEET_TO_SECONDS(RotationCoordinateFeet.START_TO_FARBALL);
    }    
    public static double FEET_TO_SECONDS(double distance)
    {
      return distance * 1;
    }
  }


}
