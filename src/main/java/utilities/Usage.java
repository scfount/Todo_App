package utilities;


import static utilities.Constants.BLUE;
import static utilities.Constants.CYAN;
import static utilities.Constants.GREEN;
import static utilities.Constants.MAGENTA;
import static utilities.Constants.RESET;

public class Usage {


  private String usage = MAGENTA + "\n\n*** Commands to Add, Complete, and Display Todos ***\n" + RESET
      + GREEN + "--csv-file <path/to/file>:\t" + RESET + "The CSV file containing the todos. This option is required.\n"
      + GREEN + "--complete-todo <id>:\t" + RESET + "Mark the Todo with the provided ID as complete.\n\n"
      + CYAN + "--add-todo:\t" + RESET + "Add a new todo. If this option is provided, then --todo-text must also be provided.\n"
      + CYAN + "--todo-text <description of todo>:\t" + RESET + "A description of the todo.\n"
      + CYAN + "--completed:\t" + RESET + "(Optional) Sets the completed status of a new todo to true.\n"
      + CYAN + "--due <due date>:\t" + RESET + "(Optional) Sets the due date of a new todo. You may choose how the date should be formatted.\n"
      + CYAN + "--priority <1, 2, or 3>:\t" + RESET + "(Optional) Sets the priority of a new todo. The value can be 1, 2, or 3.\n"
      + CYAN + "--category <a category name>:\t" + RESET + "(Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined.\n\n"
      + BLUE + "--display:\t" + RESET + "Display todos. If none of the following optional arguments are provided, displays all todos.\n"
      + MAGENTA + "*** Optional Display Arguments ***\n" + RESET
      + BLUE + "--show-incomplete:\t" + RESET + "(Optional) If --display is provided, only incomplete todos should be displayed.\n"
      + BLUE + "--show-category <category>:\t" + RESET + "(Optional) If --display is provided, only todos with the given category should be displayed.\n"
      + BLUE + "--sort-by-date:\t" + RESET + "(Optional) If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by-priority.\n"
      + BLUE + "--sort-by-priority:\t" + RESET + "(Optional) If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.\n";

  public Usage() {
  }

  @Override
  public String toString() {
    return this.usage;
  }
}
