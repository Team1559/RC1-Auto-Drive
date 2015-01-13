/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;



import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2013 extends IterativeRobot 
{
    private final int LEFTMOTOR = 1;
    private final int RIGHTMOTOR = 10;
    private Compressor c;
    private int a;
    private Talon right;
    private Talon left;
    private Joystick joy;
    private RobotDrive drive;
    private Solenoid sol1;
    private Solenoid sol2;
    double var1;
    double var2;
    double lft;
    double rgt;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        a = 1;
        System.out.println("robotInit");
        joy = new Joystick(1);
        try {
            right = new Talon(RIGHTMOTOR);
            left = new Talon(LEFTMOTOR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sol1 = new Solenoid(1);
        sol2 = new Solenoid(2);
        c = new Compressor(8,1);
        c.start();
        sol1.set(false);
        sol2.set(true);
    }  //  robotInit

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit()
    {
        a=1;
        System.out.println("autonomousInit");
            
    }   //  autonomousInit

    /**
     * This function is called periodically during operator control
     */
    
    public void autonomousPeriodic() 
    {
        
        if (a==1)
        {
            System.out.println("autonomousPeriodic");
            a++;
        }
        
    }   //  autonomousPeriodic

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() 
    {
        a=1;
        System.out.println("teleopInit");
        
    }   //  teleopInit
    
    /**
     * This function is called periodically during test mode
     */
    boolean secondGear = false;
    public void teleopPeriodic() 
    {
        if (a==1)
        {
            System.out.println("teleopPeriodic");
            a++;
        }
        if (joy.getRawButton(3) == true)
        {
            sol1.set(false);    //  1st gear override with button 3 (top)
            sol2.set(true);
        }
        else if (Math.abs(joy.getY()) >= .45 && joy.getRawButton(3) == false)   //  Auto mode
        {
            sol1.set(true);
            sol2.set(false);
            secondGear = true;
            if (Math.abs(joy.getY()) > .50 || Math.abs(joy.getY()) < .40)
            {
                secondGear = false;
            }
        }
        else if (secondGear == false && Math.abs(joy.getY()) <= .45)
        {
            sol1.set(false);
            sol2.set(true);
        
        }
        SmartDashboard.putBoolean("Second Gear", secondGear);
        
        if (joy.getY() > 0.0) {
            if (joy.getX() > 0.0) {
                lft = joy.getY() - joy.getX();
                rgt = Math.max(joy.getY(), joy.getX());
            } else {
                lft = Math.max(joy.getY(), -joy.getX());
                rgt = joy.getY() + joy.getX();
            }
        } else {
            if (joy.getX() > 0.0) {
                lft = -Math.max(-joy.getY(), joy.getX());
                rgt = joy.getY() + joy.getX();
            } else {
                lft = joy.getY() - joy.getX();
                rgt = -Math.max(-joy.getY(), -joy.getX());
            }
        }
        left.set(-lft);
        right.set(rgt);
        
       
        
    }   //  teleopPeriodic
    
    /**
     * This function is called periodically during test mode
     */
    public void testInit() 
    {
        
        a=1;
        System.out.println("testInit");
    }   //  testInit
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
        
        if (a==1)
        {
            System.out.println("testPeriodic");
            a++;
        }
    }   //  testPeriodic
    /**
     * This function is called periodically during test mode
     */
    public void disabledInit() 
    {
        a = 1;
        System.out.println("disabledInit");
        
    }   //  disabledInit
    /**
     * This function is called periodically during test mode
     */
    public void disabledPeriodic() 
    {
        
        if (a==1)
        {
            System.out.println("disabledPeriodic");
            a++;
        }
        
    }   //  disabledPeriodic
    
}