package controller.todohandler;

import commands.AbstractCmd;
import java.util.List;

/**
 * Interface representing the Controller of this system.
 */
public interface ITodoHandler {

  /**
   * Executes each command in the command list.
   *
   * @param commandList The list of Commands.
   */
  void takeAction(List<AbstractCmd> commandList);

}
