package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.upperIntake;
import frc.robot.subsystems.lowerIntake;

public class EjectCommand extends Command {
  private final upperIntake m_upper;
  private final lowerIntake m_lower;

  public EjectCommand(upperIntake upper, lowerIntake lower) {
    m_upper = upper;
    m_lower = lower;
    addRequirements(m_upper, m_lower);
  }

  @Override
  public void execute() {
    m_upper.Eject(0.8); // Push out
    m_lower.Eject(0.8);
  }

  @Override
  public void end(boolean interrupted) {
    m_upper.stop();
    m_lower.stop();
  }
}