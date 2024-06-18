package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// All interactions with real-life hardware is done in the subsystem, 
// then the command uses the subsystem to interface with the hardware.
// Think of it as a driver, like the graphics driver that your PC uses to control the GPU.

public class BaseSubsystem extends SubsystemBase {

  /* Global Variables (to use anywhere is this class) */

  // Variables inside a subsystem don't usually need to be Suppliers,
  // as they won't change unless told to by a command.

  double x;
  double y;

  boolean extended;

  public BaseSubsystem() {
    // Stuff you want to run before anything else, goes here.
    // Such as setting up a SparkMax (motor controllers),
    // Compressors for Pneumatics, etc.

  }

  // This is an instant command, which does a very basic action
  // instantly, and is only run once. Useful for things like having a button
  // that extends some pistons
  public Command baseInstantCommand() {
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          System.out.println("The Instant Command was run.");
        });
  }

  // Any public method (function) inside a subsystem can be accessed by the
  // command using it

  // Large amounts of actions can be done in one method, I just made some simple
  // ones below

  // vvv See, this one is public, you can us it from the command
  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double value) {
    // Setting a normal value to a supplier is a little goofy,
    // but this is how you do it
    x = value;
  }

  public void setY(double value) {
    y = value;
  }

  public void squareX() {
    x = Math.pow(x, 2);
  }

  public void squareY() {
    y = Math.pow(y, 2);
  }

  public void extend() {
    extended = true;
  }

  public void retract() {
    extended = false;
  }

  public boolean isExtended() {
    return extended;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run,
    // much like the periodic function in a command
  }
}
