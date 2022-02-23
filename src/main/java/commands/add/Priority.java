package commands.add;

import model.todo.Todo.Builder;
import utilities.Constants;
import utilities.ErrorLogger;

/**
 * The type Priority.
 */
public class Priority extends Add {
  private final int highPriority = 1;
  private final int lowPriority = 3;
  protected int priority;

  /**
   * Instantiates a new Priority.
   */
  public Priority() {
    super(Constants.PRIORITY_NAME, Constants.PRIORITY_CMD);
  }

  /**
   * Checks to see if the found argument is valid
   *
   * @return True if the argument is a valid priority int, false if not
   */
  @Override
  public boolean isValidArgument() {
    try {
      this.priority = Integer.parseInt(this.argument);
      if (this.priority >= highPriority && this.priority <= lowPriority)
      return true;
    } catch (NumberFormatException numberFormatException) {
      ErrorLogger.add("Please provide a valid Priority number of 1, 2, or 3");
    }
    return false;
  }

  /**
   * Uses Builder pattern to build a todo with the required argument
   *
   * @param todoBuilder the todo to build
   * @return the builder with the required argument appended
   */
  @Override
  protected Builder appendBuilder(Builder todoBuilder) {
    todoBuilder.addPriority(this.priority);
    return todoBuilder;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Priority{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
