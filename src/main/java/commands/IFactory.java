package commands;

/**
 * The interface Factory.
 */
public interface IFactory<T extends AbstractCmd> {

  /**
   * Gets the cmd from the command line for further processing
   *
   * @param cmd the cmd String
   * @return A new cmd object
   */
  T getCmd(String cmd);

  /**
   * Checks that the provided flags are adequate to run the program
   *
   * @return true if the commands are valid, false otherwise
   */
  boolean verifyCommands();

}
