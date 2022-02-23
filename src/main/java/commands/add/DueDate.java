package commands.add;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import model.todo.Todo.Builder;
import utilities.Constants;
import utilities.ErrorLogger;

/**
 * The type Due date.
 */
public class DueDate extends Add {

  protected LocalDate dueDate;

  /**
   * Instantiates a new Due date.
   */
  public DueDate() {
    super(Constants.DUEDATE_NAME, Constants.DUEDATE_CMD);
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * Checks to see if the found argument is valid
   *
   * @return True if the argument is formatted like a date, false otherwise
   */
  @Override
  public boolean isValidArgument() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
    try {
      this.dueDate = LocalDate.parse(this.argument, formatter);
      return true;
    } catch (DateTimeParseException dateTimeParseException) {
      ErrorLogger.add("Date needs to be in the format dd/mm/yyyy");
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
    todoBuilder.addDueDate(this.dueDate);
    return todoBuilder;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "DueDate{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
