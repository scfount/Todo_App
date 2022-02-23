import controller.parser.Parser;
import utilities.ErrorLogger;

/**
 * The type Main.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    ErrorLogger.create();
    Parser parser = new Parser();
    parser.processCmds(args);
  }
}
