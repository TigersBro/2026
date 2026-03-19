package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.upperIntake;
import frc.robot.subsystems.lowerIntake;

public class IntakeCommand extends Command {
  private final upperIntake m_upper;
  private final lowerIntake m_lower;

  public IntakeCommand(upperIntake upper, lowerIntake lower) {
    m_upper = upper;
    m_lower = lower;
    // Require both so no other command can move them
    addRequirements(m_upper, m_lower);
  }

  @Override
  public void execute() {
    m_upper.Intake(0.8); // Pull in
    m_lower.Intake(0.8);
  }

  @Override
  public void end(boolean interrupted) {
    m_upper.stop();
    m_lower.stop();
  }
}