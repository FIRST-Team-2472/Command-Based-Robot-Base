package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.BaseCommand;
import frc.robot.subsystems.BaseSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

// The RobotContainer is where all the Subsystems, Commands, InstantCommands,
// Triggers, and Controllers are set up. NO LOGICAL CODE IS TO BE ADDED HERE (or
// VERY little, if ABSOLUTELY needed).
// This is where Subsystems are told their default commands, and where button
// presses are assigned actions
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final BaseSubsystem baseSubsystem = new BaseSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  // If you see XboxController in other code, CommandXboxController is the same,
  // just with some more command functionality
  private final CommandXboxController xbox = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Define the BaseSubsystem's default command to the BaseCommand
    // We have to use Lambdas (the '() ->') to set the value of the Suppliers
    baseSubsystem.setDefaultCommand(new BaseCommand(baseSubsystem, () -> xbox.getRightX(), () -> xbox.getRightY()));

    configureBindings();
  }

  private void configureBindings() {
    // Run the baseInstantCommand inside of the BaseSubsystem when isExtended
    // changes from false to true
    new Trigger(baseSubsystem::isExtended)
        .onTrue(baseSubsystem.baseInstantCommand());

    // Run baseInstantCommand when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    xbox.b().whileTrue(baseSubsystem.baseInstantCommand());
  }

  // This is where the command that is run during autonomous mode, such as
  // 'autos', is returned
  public BaseCommand getAutonomousCommand() {
    // There is currently no command we want to run in autonomous, so we return null
    return null;
  }
}
