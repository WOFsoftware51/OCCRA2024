// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Global_Variables 
{
    public static double drivePercentModifier = 0.8;
    public static double rotationPercentModifier = 0.5;
    public static boolean boostOn = false;
    public static boolean isShooting = false;
    private static DigitalInput m_sensorBottom = new DigitalInput(3);
    private static DigitalInput m_sensorTop = new DigitalInput(2);
    public static SendableChooser<Double> testAutonTimer = new SendableChooser<>();
    public static SendableChooser<Double> velocitySendableChooser = new SendableChooser<>();
    public static SCORING_MODE currentScoringMode = SCORING_MODE.BASKET;
    public static boolean isUsingGyro = true;

    /**@return 1 if has piece
     *  <li> -1 if doesn't have piece
     */
    public static int getSensorVal(){
        if(m_sensorBottom.get() || m_sensorTop.get()) {
            return 1;
        }
        else{
            return -1;
        }
    }

    public enum SCORING_MODE
    {
        BASKET,
        HUMAN_PLAYER,
    }
}

