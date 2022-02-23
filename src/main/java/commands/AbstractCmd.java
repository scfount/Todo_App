package commands;

import java.util.List;
import java.util.Objects;
import utilities.ErrorLogger;

/**
 * The type Abstract cmd.
 */
public abstract class AbstractCmd {

  protected String name;
  protected String command;
  protected String argument;


  /**
   * Instantiates a new Abstract cmd.
   *
   * @param name    the name
   * @param command the command
   */
  public AbstractCmd(String name, String command) {
    this.name = name;
    this.command = command;
  }

  /**
   * Find argument for specified command
   *
   * @param cmdList the cmd list
   * @return True if the argument for the command is found, otherwise false
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  public boolean findArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    if (cmdList.contains(this.command)) {
      try {
        int argIndex = cmdList.indexOf(this.command) + 1;
        this.argument = cmdList.get(argIndex);
        if (this.isValidArgument()) {
          return true;
        }
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        ErrorLogger.add(this.name + " argument not provided");
      }
    }
    return false;
  }

  /**
   * Checks to see if the found argument is valid
   *
   * @return True if the argument does not start like a command, false if it does
   */
  public boolean isValidArgument() {
    if (this.argument.startsWith("--")) {
      ErrorLogger.add(this.name + " argument not provided");
      return false;
    } else return true;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets command.
   *
   * @return the command
   */
  public String getCommand() {
    return this.command;
  }

  /**
   * Gets argument.
   *
   * @return the argument
   */
  public String getArgument() {
    return this.argument;
  }

  /**
   * Compares to objects for equality
   *
   * @param o the other object to compare to
   * @return true if equal, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractCmd that = (AbstractCmd) o;
    return Objects.equals(this.name, that.name) && Objects.equals(this.argument, that.argument);
  }

  /**
   * returns the hashcode for an object
   *
   * @return the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, argument);
  }

}
