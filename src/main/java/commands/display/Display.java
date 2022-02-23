package commands.display;

import commands.AbstractCmd;
import java.util.ArrayList;
import java.util.List;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

/**
 * The type Display todo.
 */
public class Display extends AbstractCmd implements IDisplay {

  protected static DisplayFactory displayFactory = new DisplayFactory();
  protected List<Display> displayProperties = new ArrayList<>();
  protected boolean displayStatus;

  /**
   * Instantiates a new Display.
   *
   * @param name    the name
   * @param command the command
   */
  public Display(String name, String command) {
    super(name, command);
  }

  /**
   * Gets display properties.
   *
   * @return a List of display objects
   */
  public List<Display> getDisplayProperties() {
    return this.displayProperties;
  }

  /**
   * Find argument for specified command
   *
   * @param cmdList the cmd list
   * @return True since this command has no argument
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  @Override
  public boolean findArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    for (String userCmd : cmdList) {
      Display cmd = displayFactory.getCmd(userCmd);
      if (cmd != null) {
        if (cmd.getName() == Constants.SHOW_CATEGORY_NAME) {
          cmd.findAddArgument(cmdList);
        }
        cmd.displayStatus = true;
        this.displayProperties.add(cmd);
      }
    }
    if (!displayFactory.verifyCommands()) {
      ErrorLogger.add("Only 1 sort method allowed");
    }
    return true;
  }

  /**
   * Finds the argument for the display command if applicable
   *
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  protected void findAddArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    return;
  }

  /**
   * Applies a specific filter or sort on a list of todos
   *
   * @param todoList the todo list
   * @return the list with the display property applied
   */
  @Override
  public List<Todo> applyDisplayProperty(List<Todo> todoList) {
    return null;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Display{" +
        "displayProperties=" + displayProperties +
        '}';
  }
}
