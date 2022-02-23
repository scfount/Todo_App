package utilities;

import static utilities.Constants.RED;
import static utilities.Constants.RESET;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Error logger, which will track all errors that occur.
 */
public class ErrorLogger {


  public static List<String> log;

  /**
   * Instantiates a new Error logger.
   */
  private ErrorLogger() {
  }

  /**
   * Create a new Error Logger List
   */
  public static void create() {
    log = new ArrayList<>();
  }


  /**
   * Adds an error to the ErrorLogger.
   *
   * @param event A string description of the event.
   */
  public static void add(String event) {
    if (!log.contains(event)) {
      log.add(event);
    }
  }

  /**
   * Checks whether this ErrorLogger is empty.
   *
   * @return true if this ErrorLogger is empty, false otherwise.
   */
  public static boolean isEmpty() {
    return log.size() == 0;
  }

  /**
   * Returns the number of elements/errors in the logger
   *
   * @return the number of elements/errors in the logger
   */
  public static int getSize() {
    return log.size();
  }


  /**
   * Prints all the errors in the log
   */
  public static void printLog() {
    System.out.print(RED);
    System.out.println("ERROR LOG:");
    log.forEach(System.out::println);
    System.out.print(RESET);
  }
}