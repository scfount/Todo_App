package commands;

import utilities.Constants;
import utilities.ErrorLogger;

/**
 * The type Complete todo.
 */
public class Complete extends AbstractCmd {

  private int id;

  /**
   * Instantiates a new Complete todo.
   */
  public Complete() {
    super(Constants.COMPLETE_TODO_NAME, Constants.COMPLETE_TODO_CMD);
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Checks to see if the found argument is valid
   *
   * @return True if the argument does not start like a command, false if it does
   */
  @Override
  public boolean isValidArgument() {
    try {
      this.id = Integer.parseInt(this.argument);
      if (this.id >= 1) {
        return true;
      }
    } catch (NumberFormatException numberFormatException) {
      ErrorLogger.add("Please provide a valid number ID");
    }
    ErrorLogger.add("Please provide an ID greater than 0");
    return false;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Complete{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
