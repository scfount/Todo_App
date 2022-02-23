package commands;

import commands.add.Add;
import commands.display.Display;
import utilities.Constants;
import utilities.ErrorLogger;


/**
 * The type Comm factory.
 */
public class CmdFactory {
  private boolean add = false;
  private boolean complete = false;
  private boolean csv = false;
  private boolean display = false;

  public CmdFactory() {
  }

  /**
   * Returns a specific Abstract Comm type based on the argument
   *
   * @param cmd the cmd String
   * @return a specific type of Abstract Comm, or null
   */
  public AbstractCmd getCmd(String cmd) {
    if (cmd == null || cmd.isEmpty()) {
      return null;
    }
    if (cmd.equals(Constants.CSV_FILE_CMD)) {
      this.csv = true;
      return new CSVFile();
    }
    else if (cmd.equals(Constants.ADD_TODO_CMD)) {
      this.add = true;
      return new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    }
    else if (cmd.equals(Constants.COMPLETE_TODO_CMD)) {
      this.complete = true;
      return new Complete();
    }
    else if (cmd.equals(Constants.DISPLAY_CMD)) {
      this.display = true;
      return new Display(Constants.DISPLAY_NAME, Constants.DISPLAY_CMD);
    }
    return null;
  }

  /**
   * Checks that the provided flags are adequate to run the program
   *
   * @return true if the commands are valid, false otherwise
   */
  public boolean verifyCommands() {
    if (this.csv) {
      if (this.add || this.complete || this.display) {
        return true;
      } else {
        ErrorLogger.add("Insufficient commands provided");
      }
    } else {
      ErrorLogger.add("Insufficient commands provided");
    }
    return false;
  }
}
