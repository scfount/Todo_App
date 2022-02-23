package commands.add;

import commands.IFactory;
import utilities.Constants;

/**
 * The type Add factory.
 */
public class AddFactory implements IFactory {
  private boolean text = false;

  @Override
  public Add getCmd(String cmd) {
    if (cmd == null || cmd.isEmpty()) {
      return null;
    } else if (cmd.equals(Constants.TEXT_CMD)) {
      this.text = true;
      return new Text();
    } else if (cmd.equals(Constants.COMPLETED_CMD)) {
      return new Completed();
    } else if (cmd.equals(Constants.DUEDATE_CMD)) {
     return new DueDate();
    } else if (cmd.equals(Constants.PRIORITY_CMD)) {
      return new Priority();
    } else if (cmd.equals(Constants.CATEGORY_CMD)) {
      return new Category();
    }
    return null;
  }

  /**
   * Checks that the provided flags are adequate to run the program
   *
   * @return true if the commands are valid, false otherwise
   */
  public boolean verifyCommands() {
    return this.text;
  }
}
