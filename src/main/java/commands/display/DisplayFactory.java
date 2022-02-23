package commands.display;

import commands.IFactory;
import utilities.Constants;

/**
 * The type Display factory.
 */
public class DisplayFactory implements IFactory {
  private final int MAX_SORT = 2;
  private int sortCount = 0;


  /**
   * Gets the cmd from the command line for further processing
   *
   * @param cmd the cmd String
   * @return A new cmd object
   */
  @Override
  public Display getCmd(String cmd) {
    if (cmd == null || cmd.isEmpty()) {
      return null;
    } else if (cmd.equals(Constants.SHOW_INCOMPLETE)) {
      return new ShowIncomplete();
    } else if (cmd.equals(Constants.SHOW_CATEGORY)) {
      return new ShowCategory();
    } else if (cmd.equals(Constants.DATE_SORT)) {
      this.sortCount++;
      return new DateSort();
    } else if (cmd.equals(Constants.PRIORITY_SORT)) {
      this.sortCount++;
      return new PrioritySort();
    } else {
      return null;
    }
  }

  /**
   * Checks that the provided flags are adequate to run the program
   *
   * @return true if the commands are valid, false otherwise
   */
  public boolean verifyCommands() {
    return this.sortCount < MAX_SORT;
  }


  /**
   * Gets the count for how many sort properties have been applied
   *
   * @return the count
   */
  public int getSortCount() {
    return sortCount;
  }
}
