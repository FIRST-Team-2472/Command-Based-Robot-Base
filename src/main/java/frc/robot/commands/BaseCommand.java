package frc.robot.commands;

import frc.robot.subsystems.BaseSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

// Commands is where all the computing and logic takes place.
// A command can use multiple subsystems.
// All user input will also pass through a command before causing movement.

public class BaseCommand extends Command {

  /* Global Variables (to use anywhere is this class) */

  private final BaseSubsystem baseSubsystem;

  // Suppliers automatically update their values when changed, so we don't have to
  // read again.
  // We always use doubles for our values
  Supplier<Double> x;
  Supplier<Double> y;

  // Check out
  // https://www.geeksforgeeks.org/supplier-interface-in-java-with-examples/ if
  // you have trouble with Suppliers

  // This variable doesn't need to be a supplier, but why not?
  Supplier<Boolean> extended;

  public BaseCommand(BaseSubsystem baseSubsystem, Supplier<Double> x, Supplier<Double> y) {
    this.baseSubsystem = baseSubsystem;

    this.x = x;
    this.y = y;

    /*
     * `addRequirements()` adds a subsystem as requirements of this command.
     * This will also cause all other commands using the subsystem to end.
     * This makes your command "possess" or "own" the system.
     */
    addRequirements(baseSubsystem);
  }

  /*
   * Called when the command is initially created.
   * Stick tests, setup code, or other things you want to run *before* the command
   * starts. This is *not* called when the robot starts.
   */
  @Override
  public void initialize() {

    // Make sure we start retracted
    baseSubsystem.retract();
  }

  /*
   * Run every 40ms during robot runtime, after the command has been started.
   * Stick calculations and action-taking code in here to be run.
   */
  @Override
  public void execute() {

    // Send our input to the subsystem
    baseSubsystem.setX(x.get());
    baseSubsystem.setY(y.get());

    // Read the current values from the subsystem
    System.out.println("(" + baseSubsystem.getX() + ", " + baseSubsystem.getY() + ")");

  }

  /*
   * This is the same as `initialize()`, but run when the command is told to stop,
   * or when you stop it. Good for stopping motors and cleaning up. This is also
   * called when the command is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
  }

  /*
   * When you want to end the command, make this method return true.
   * You make this return true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
