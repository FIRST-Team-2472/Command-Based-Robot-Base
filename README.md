# Example Command Based FRC Robot Structure

This is an example command-based robot template for explaining the function, structure, and creation of command-based robots.

## Usage

To run this code, deploy to the robot using the `Deploy Robot Code` button. Then open  `FRC Driver Station` and connect a XBox controller to your computer, making sure its ID is 0 inside the USB menu. Click the green `Enable` button.

Once its been enabled, the robot will print `(0, 0)`, moving the right joystick on the xbox controller will cause the values to reflect the `x` and `y` positions of the joystick.

## How to make a Command-Based Robot

### 1. Start with the Subsystem

When implementing a new part of the robot, start with writing the methods that will directly interact with the hardware. This should be written in a subsystem with an ***appropriate*** name ending with Subsystem. An example is `SwerveSubsystem.java`. Then writing the command comes next. In a command you include the logic of an operation you want the robot to do. Each command should only perform one action, such as moving a turret up and down, launching a projectile, etc. Now, if two actions must happen at the same time, such as launching and moving a turret, either both actions must be merged into a new command, potentially using a [`SequentialCommandGroup`](https://docs.wpilib.org/en/2021/docs/software/commandbased/command-groups.html#sequentialcommandgroup) (a [`ParallelCommandGroup`](https://docs.wpilib.org/en/2021/docs/software/commandbased/command-groups.html#parallelcommandgroup) probably won't work). Another option is to split the subsystem into smaller parts, so both parts can be used at the same time. (two commands cannot use the same subsystem at the same time ``:(`` )

When making a command you have to think of the following "stuff":

1. What needs to be initialized? Any values that need to be reset? Though, must motor controller and pneumatics will be initialized in their subsystem's constructor.

2. What needs to be executed? Now **DO NOT** put long `while` or `for` loops in `execute()`, this will cause the RoboRIO to freeze, because it can't do **anything** until the loop is done. So no values from sensors or to motors can be updated until the loop ends.